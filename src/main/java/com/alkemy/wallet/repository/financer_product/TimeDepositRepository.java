package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.TimeDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeDepositRepository extends JpaRepository<TimeDeposit, Integer> {

    @Query("SELECT td FROM TimeDeposit td JOIN FETCH td.account a WHERE a.id = :accountId")
    List<TimeDeposit> findAllByAccountId(@Param("accountId") int accountId);

    @Query("SELECT td FROM TimeDeposit td JOIN FETCH td.account a JOIN FETCH a.user u WHERE u.id = :userId")
    List<TimeDeposit> findAllByUserId(@Param("userId") int userId);

    Optional<TimeDeposit> findByExpirationDate(LocalDate expirationDate);

}
