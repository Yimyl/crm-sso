package edu.bjtu.crm.sso.service;

import edu.bjtu.crm.sso.domain.model.Product;

import java.util.List;

public interface ProductMngService {
    int addProduct(Product product);

    Product findProductByName(String name);

    Product findProductById(long id);

    List<Product> findProductByProduct(Product product);

    int updateProduct(Product product);

    int deleteProductByName(String name);
}
