package com.alkemy.wallet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.account.AccountType;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.account.AccountTypeRepository;
import com.alkemy.wallet.repository.user.UserRepository;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected AccountTypeRepository accountTypeRepository;

    @Autowired
    protected UserRepository userRepository;

    // Mapeo principal para AccountDTO extendido
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "userName", expression = "java(account.getUser() != null && account.getUser().getPerson() != null ? account.getUser().getPerson().getName() + \" \" + account.getUser().getPerson().getLastName() : account.getUser().getUsername())")
    @Mapping(target = "accountType", expression = "java(formatAccountType(account))")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "creationDate", expression = "java(account.getCreationDate() != null ? account.getCreationDate().toString() : null)")
    public abstract AccountDTO toDTO(Account account);

    public abstract List<AccountDTO> toDTOList(List<Account> accounts);

    // Mapeo inverso (no se toca)
    @Mapping(target = "accountType", expression = "java(accountTypeFromString(accountDTO.getAccountType()))")
    @Mapping(target = "user", expression = "java(userFromId(accountDTO.getUserId()))")
    public abstract Account toEntity(AccountDTO accountDTO);

    // Método auxiliar usando el repositorio
    protected AccountType accountTypeFromString(String name) {
        if (name == null)
            return null;
        return accountTypeRepository.findByAccountType(name);
    }

    protected User userFromId(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Formatea el tipo de cuenta como texto amigable
    protected String formatAccountType(Account account) {
        if (account.getAccountType() == null)
            return null;
        String tipo = "";
        String tipoRaw = account.getAccountType().getAccountType().toLowerCase();
        switch (tipoRaw) {
            case "caja de ahorro":
                tipo = "Caja de ahorro";
                break;
            case "cuenta corriente":
                tipo = "Cuenta corriente";
                break;
            case "cuenta de inversion":
                tipo = "Cuenta de inversión";
                break;
            default:
                tipo = account.getAccountType().getAccountType();
        }
        if (account.getCurrency() != null) {
            tipo += " en " + (account.getCurrency().toString().equals("ARS") ? "pesos" : "dólares");
        }
        return tipo;
    }
}
