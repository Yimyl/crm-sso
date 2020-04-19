package edu.bjtu.crm.sso.service.impl;

import edu.bjtu.crm.sso.dao.mapper.ProductMapper;
import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.service.ProductMngService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductMngServiceImpl implements ProductMngService {
    @Resource
    private ProductMapper productMapper;


    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }


    @Override
    public Product findProductByName(String name) {
        return productMapper.findProductByName(name);
    }

    @Override
    public Product findProductById(long id) {
        return productMapper.findProductById(id);
    }


    @Override
    public List<Product> findProductByProduct(Product product) {
        return productMapper.findProductByProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public int deleteProductByName(String name) {
        return productMapper.deleteProductByName(name);
    }
}
