package com.alkemy.wallet.services.financer_product;


import com.alkemy.wallet.models.financer_product.FinancerProduct;

import java.util.List;

public  interface FinancerProductService<T extends FinancerProduct> {

    public T save(T financerProduct);
    public T getById(int id);
    public void deleteById(int id);

    //public T edit(int id, T newFinancerProductData); //no le encuentro uso al editar un producto financiero, se supone que es un contrato que no se va a romper hasta vencimiento o pago...

    public List<T> getAllByAccountId(int accountId);
    public List<T> getAllByUserId(int userId);

}
