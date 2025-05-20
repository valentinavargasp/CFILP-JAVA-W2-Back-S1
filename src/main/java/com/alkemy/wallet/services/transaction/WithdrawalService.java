package com.alkemy.wallet.services.transaction;

import java.util.List;

import com.alkemy.wallet.dto.WithdrawalDTO;


public interface WithdrawalService{
    public List<WithdrawalDTO> getByBranch(String branch);

    public List<WithdrawalDTO> getByMethod(String method);
}
