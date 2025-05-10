package com.alkemy.wallet.services;


import com.alkemy.wallet.models.Account;

public interface AccountService {
    //TODO Metodos para crear, editar, borrar, buscar cuentas

    public void createAccount(Account account);

    public void deleteAccountById(int id);

    public void editAccount(Account account);

    public Account getAccountById(int id);
}
