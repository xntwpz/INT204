package com.example.test2405.controllers;

import com.example.test2405.dtos.ProductsDTO;
import com.example.test2405.entities.Product;
import com.example.test2405.services.ProductService;
import com.example.test2405.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService,SupplierService supplierService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public ResponseEntity<Set<ProductsDTO>> getProducts() {
        List<Product> products = productService.getProducts();
        Set<ProductsDTO> result = products.stream().map(
                s -> modelMapper.map(s, ProductsDTO.class)
        ).collect(Collectors.toSet());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product id %d does not exist.", id));
        }
        ProductsDTO productsDTO = modelMapper.map(product, ProductsDTO.class);
        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product editedproduct = productService.editProduct(id, product);
        if (editedproduct != null) {
            ProductsDTO responseDTO = modelMapper.map(editedproduct, ProductsDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}