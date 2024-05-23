package com.example.test2405.services;

import com.example.test2405.entities.Product;
import com.example.test2405.entities.Supplier;
import com.example.test2405.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ProductService productService;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, ProductService productService) {
        this.supplierRepository = supplierRepository;
        this.productService = productService;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSuppliersById(Integer supplierId){
        return supplierRepository.findById(supplierId).orElse(null);
    }
    public List<Product> getSupplierProducts(Integer supplierId) {
        if (!supplierRepository.existsById(supplierId)) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "supplier id ' " + supplierId + "' NOT FOUND !!"
            );
        }
        return productService.getProductsBySupplier(supplierId);
    }

    @Transactional
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Transactional
    public Product addProductForSupplier(Integer supplierId, Product product) {
        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Supplier id '" + supplierId + "' NOT FOUND!!!"
            );
        }
        product.setSupplier(supplier);
        return productService.addProduct(product);
    }

    @Transactional
    public Supplier editSupplier(Integer supplierId, Supplier supplier) {
        Supplier editSupplier = supplierRepository.findById(supplierId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (editSupplier != null) {
            // update
            editSupplier.setCompanyName(supplier.getCompanyName());
            editSupplier.setContactName(supplier.getContactName());
            editSupplier.setContactTitle(supplier.getContactTitle());
            editSupplier.setCountry(supplier.getCountry());
            editSupplier.setPhone(supplier.getPhone());
            editSupplier.setFax(supplier.getFax());

            return supplierRepository.save(editSupplier);
        }
        return null;
    }
    @Transactional
    public void deleteSupplier(Integer supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "supplier with ID " + supplierId + " does not exist"));
        supplierRepository.delete(supplier);
    }
}
