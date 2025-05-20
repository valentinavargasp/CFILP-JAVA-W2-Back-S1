package com.alkemy.wallet.services.transaction;

import java.util.List;

import com.alkemy.wallet.dto.WithdrawalDTO;
import com.alkemy.wallet.models.transaction.Withdrawal;

public interface WithdrawalService {
    public List<WithdrawalDTO> getByBranch(String branch);

    public List<WithdrawalDTO> getByMethod(String method);

    public List<WithdrawalDTO> getByUserId(int userId);

    public WithdrawalDTO save(Withdrawal withdrawal);

}
