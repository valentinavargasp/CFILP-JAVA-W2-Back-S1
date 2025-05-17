package com.alkemy.wallet.services.financer_product.impl;

import com.alkemy.wallet.models.financer_product.FinancerProduct;
import com.alkemy.wallet.services.financer_product.FinancerProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class FinancerProductServiceImpl<T extends FinancerProduct> implements FinancerProductService<T> {


    protected final JpaRepository<T, Integer> repository;

    public FinancerProductServiceImpl(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }


    @Override
    public T save(T financerProduct) {
        if (financerProduct.getId() != null && repository.existsById(financerProduct.getId())) {
            throw new IllegalArgumentException("El producto ya existe con ID: " + financerProduct.getId());
        }
        try{
            return repository.save(financerProduct);
        }catch(Exception e){
            throw new RuntimeException("Error al guardar el producto", e);
        }

    }

    @Override
    public T getById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }else{
            try{
                repository.deleteById(id);
            }catch(Exception e){
                throw new RuntimeException("Error al eliminar el producto", e);
            }
        }
    }

    @Override
    public List<T> getAllByAccountId(int accountId) {
     try{
         return repository.findAll().stream()
                 .filter(p -> p.getAccount().getId() == accountId)
                 .toList();
     }catch(Exception e){
         throw new RuntimeException("Error al obtener los productos", e);
     }
    }

    @Override
    public List<T> getAllByUserId(int userId) {
        try{
            return repository.findAll().stream()
                    .filter(p -> p.getAccount().getUser().getId() == userId)
                    .toList();
        }catch(Exception e){
            throw new RuntimeException("Error al obtener los productos", e);
        }
    }


}
