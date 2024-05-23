package com.example.test2405.services;

import com.example.test2405.entities.Product;
import com.example.test2405.entities.Supplier;
import com.example.test2405.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProductsBySupplier(Integer supplierId){
        return productRepository.findBySupplierId(supplierId);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public Product editProduct(Integer id , Product product){
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setUnitPrice(product.getUnitPrice());
            existingProduct.setPackageField(product.getPackageField());
            existingProduct.setDiscontinued(product.getDiscontinued());
            existingProduct.setSupplier(product.getSupplier());
            return productRepository.save(existingProduct);
        }
        return null;
    }

}
