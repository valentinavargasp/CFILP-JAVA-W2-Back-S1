package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.TimeDeposit;
import com.alkemy.wallet.repository.account.AccountRepository;
import com.alkemy.wallet.repository.financer_product.TimeDepositRepository;
import com.alkemy.wallet.services.financer_product.TimeDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeDepositServiceImpl implements TimeDepositService {


    private final TimeDepositRepository timeDepositRepository;

    @Override
    public TimeDeposit getByExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula o vacía");
        }

        return timeDepositRepository.findByExpirationDate(expirationDate)
                .orElseThrow(() -> new RuntimeException("No se encontró plazo fijo con fecha de vencimiento: " + expirationDate));


    }

    @Override
    public TimeDeposit save(TimeDeposit timeDeposit) {
        if (timeDeposit.getId() != null && timeDepositRepository.existsById(timeDeposit.getId())) {
            throw new IllegalArgumentException("El plazo fijo ya existe con ID: " + timeDeposit.getId());
        }

        try{
            //verifica que haya saldo en la cuenta
            if(timeDeposit.getAccount().getBalance() <= timeDeposit.getDepositAmount() || timeDeposit.getAccount().getBalance() < 0 ){
                throw new IllegalArgumentException("El saldo no puede ser menor o igual que el monto a depositar en plazo fijo");
            }else{
                timeDeposit.getAccount().setBalance(timeDeposit.getAccount().getBalance() - timeDeposit.getDepositAmount());
            }
            return timeDepositRepository.save(timeDeposit);
        }catch(Exception e){
            throw new RuntimeException("Error al guardar el plazo fijo", e);
        }
    }

    @Override
    public TimeDeposit getById(int id) {
        try {
            return timeDepositRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Plazo fijo no encontrado con ID: " + id));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        timeDepositRepository.deleteById(id);
    }

    @Override
    public List<TimeDeposit> getAllByAccountId(int accountId) {
        try{
            return timeDepositRepository.findAll().stream()
                    .filter(p -> p.getAccount().getId() == accountId)
                    .toList();
        }catch(Exception e){
            throw new RuntimeException("Error al obtener los plazos fijos", e);
        }
    }

    @Override
    public List<TimeDeposit> getAllByUserId(int userId) {
        try{
            return timeDepositRepository.findAll().stream()
                    .filter(p -> p.getAccount().getUser().getId() == userId)
                    .toList();
        }catch(Exception e){
            throw new RuntimeException("Error al obtener los plazos fijos", e);
        }
    }


}
