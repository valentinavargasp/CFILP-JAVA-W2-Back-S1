package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.account.Account;
import com.alkemy.wallet.models.financer_product.Loan;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.financer_product.LoanRepository;
import com.alkemy.wallet.services.financer_product.LoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;

    @Override
    public Loan save(Loan loan) {
        if (loan.getId() != null && loanRepository.existsById(loan.getId())) {
            throw new IllegalArgumentException("El prestamo ya existe con ID: " + loan.getId());
        }
        
        try{
            //Aumenta el saldode la cuenta al guardar el prestamo   
            loan.getAccount().setBalance(loan.getAccount().getBalance() + loan.getLoanAmount());
            return loanRepository.save(loan);
        }catch(Exception e){
            throw new RuntimeException("Error al guardar el prestamo", e);
        }
    }

    @Override
    @Transactional
    public void payLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + loanId));


        /* //TODO: agregar un metodo para marcar el prestamo como pago o activo.
        if (loan.isPaid()) {

            throw new IllegalStateException("El préstamo ya está pagado");
        }
        */


        // Obtener la cuenta actualizada de la base de datos
        Account account = accountRepository.findById(loan.getAccount().getId())
                .orElseThrow(() -> new IllegalStateException("Cuenta no encontrada"));
        
        double loanAmount = loan.getLoanAmount();
        
        if (account.getBalance() < loanAmount) {
            throw new IllegalStateException("Saldo insuficiente para pagar el préstamo");
        }
        
        try {
            // Actualizar solo el balance de la cuenta
            account.setBalance(account.getBalance() - loanAmount);

            // Marcar el préstamo como pagado
            // loan.setPaid(true);
            
            // Guardar los cambios
            accountRepository.save(account);
            loanRepository.save(loan);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el pago del préstamo", e);
        }
    }


    @Override
    public Loan getById(int id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + id));
    }

    @Override
    public void deleteById(int id) {
        try {
            loanRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al borrar el prestamo", e);
        }
    }

    @Override
    public List<Loan> getAllByAccountId(int accountId) {
        if(!accountRepository.existsById(accountId)){
            throw new IllegalArgumentException("La cuenta no existe con ID: " + accountId);
        }
        try {
            return loanRepository.findAll().stream()
                    .filter(p -> p.getAccount().getId() == accountId)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los prestamos de la cuenta con id: "+accountId, e);
        }
    }

    @Override
    public List<Loan> getAllByUserId(int userId) {
        if(!accountRepository.existsById(userId)){
            throw new IllegalArgumentException("El usuario no existe con ID: " + userId);
        }
        try {
            return loanRepository.findAll().stream()
                    .filter(p -> p.getAccount().getUser().getId() == userId)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los prestamos del usuaario con id: "+userId, e);
        }
    }
}