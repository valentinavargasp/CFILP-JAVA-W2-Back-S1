package com.alkemy.wallet.services;


import com.alkemy.wallet.models.Account;



public interface AccountService {
    //TODO Metodos para crear, editar, borrar, buscar cuentas

    public Account createAccount(Account account);

    public void deleteAccountById(int id);

    public Account editAccount(int id, Account newAccountData);

    public Account getAccountById(int id);
}
