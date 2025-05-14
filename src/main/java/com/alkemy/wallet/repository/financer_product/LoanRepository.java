package com.alkemy.wallet.repository.financer_product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {


}
