package com.alkemy.wallet.controllers;

import com.alkemy.wallet.models.financer_product.FinancerProduct;
import com.alkemy.wallet.services.financer_product.FinancerProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;

@RestController
@RequestMapping("/api/financer-products")
@Tag(name = "Productos Financieros", description = "Gestión de productos financieros")
public class FinancerProductController<T extends FinancerProduct> {

    private final FinancerProductService<T> financerProductService;

    @Autowired
    public FinancerProductController(FinancerProductService<T> financerProductService) {
        this.financerProductService = Objects.requireNonNull(financerProductService, 
            "financerProductService no puede ser null");
    }



}