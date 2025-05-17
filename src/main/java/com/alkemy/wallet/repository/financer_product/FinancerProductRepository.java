package com.alkemy.wallet.repository.financer_product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.financer_product.FinancerProduct;

public interface FinancerProductRepository extends JpaRepository<FinancerProduct, Integer> {

    List<FinancerProduct> findByAccountId(int accountId);

}
