package com.alkemy.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.FinancerProduct;

public interface FinancerProductRepository extends JpaRepository<FinancerProduct, Integer> {

}
