package com.example.classicmodel2305.services;

import com.example.classicmodel2305.entities.Office;
import com.example.classicmodel2305.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

// 1. told springboot this is services
@Service
public class OfficeService {
    // 2.dnt forget autowired for

    // old
//    @Autowired
//    private OfficeRepository repository;

    // new
    private final OfficeRepository repository;

    @Autowired
    public OfficeService(OfficeRepository repository) {
        this.repository = repository;
    }

    // get all
    public List<Office> getAllOffice() {
        return repository.findAll();
    }

    // get by id
    public Office getOffice(String officeCode) {
        return repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Officed" + officeCode + " DOES NOT EXIST !!!") {
                }
        );
    }

    // create
    @Transactional
    public Office createNewOffice(Office office) {
        return repository.save(office);
    }

    // delete
    @Transactional
    public void removeOffice(String officeCode) {
        Office office = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Officed" + officeCode + "DOES NOT EXIST !!!")
        );
        repository.delete(office);
    }

    // update
    @Transactional
    public Office updateOffice(String officeCode, Office office) {
        if (office.getOfficeCode() != null && !office.getOfficeCode().trim().isEmpty()) {
            if (!office.getOfficeCode().equals(officeCode)) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                        "Conflict Office code  !!! (" + officeCode + "vs" + office.getOfficeCode() + ")");
            }
        }
        Office existingOffice = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Office id + " + officeCode + "Does not exist!!")
        );
        return repository.save(office);
    }
}
