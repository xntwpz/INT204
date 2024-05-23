package com.example.test2405.controllers;

import com.example.test2405.dtos.ProductsDTO;
import com.example.test2405.dtos.SuppliersDTO;
import com.example.test2405.entities.Product;
import com.example.test2405.entities.Supplier;
import com.example.test2405.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    // dont forget configuration
    @Autowired
    public SupplierController(SupplierService supplierService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ResponseEntity<Set<SuppliersDTO>> getSuppliers() {
        List<Supplier> suppliers = supplierService.getSuppliers();
        Set<SuppliersDTO> results = suppliers.stream().map(s -> modelMapper
                .map(s, SuppliersDTO.class)).collect(Collectors.toSet());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{supplierId}/products")
    public ResponseEntity<Set<ProductsDTO>> getSupplierProducts(@PathVariable Integer supplierId) {
        List<Product> products = supplierService.getSupplierProducts(supplierId);
        Set<ProductsDTO> results = products.stream().map(p -> modelMapper.map(p, ProductsDTO.class)).collect(Collectors.toSet());
        return ResponseEntity.ok(results);
    }

    @PostMapping("/{supplierId}/products")
    public ResponseEntity<ProductsDTO> addProductForSupplier(@PathVariable Integer supplierId, @RequestBody Product product) {
        ProductsDTO result = modelMapper.map(supplierService.addProductForSupplier(supplierId, product), ProductsDTO.class);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<SuppliersDTO> addSupplier(@RequestBody Supplier supplier) {
        SuppliersDTO result = modelMapper.map(supplierService.addSupplier(supplier), SuppliersDTO.class);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> editSupplier(@PathVariable Integer supplierId, @RequestBody Supplier supplier){
        Supplier editedSupplier = supplierService.editSupplier(supplierId,supplier);
        return ResponseEntity.ok(editedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer supplierId){
        try {
            supplierService.deleteSupplier(supplierId);
            // message in inspect browser
            return new ResponseEntity<>("The supplier has been deleted" , HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e){
            return new ResponseEntity<>("An error has occurred, the supplier does not exist" , HttpStatus.NOT_FOUND);
        }
    }
}
