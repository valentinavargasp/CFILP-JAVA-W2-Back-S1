package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {


}
