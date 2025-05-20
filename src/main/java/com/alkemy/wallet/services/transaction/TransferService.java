package com.alkemy.wallet.services.transaction;

import java.util.List;

import com.alkemy.wallet.dto.TransferDTO;



public interface TransferService { 
    public List<TransferDTO> getByDestinationAccountId(int accountId);
}
