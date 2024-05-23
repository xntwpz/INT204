package com.example.classicmodel2305.services;

import com.example.classicmodel2305.entities.Employee;
import com.example.classicmodel2305.entities.Office;
import com.example.classicmodel2305.repositories.EmployeeRepository;
import com.example.classicmodel2305.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    // get all
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    // get by id
    public Employee getEmployee(Integer employeeNumber){
        return repository.findById(employeeNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Employees" + employeeNumber + " DOES NOT EXIST !!!"){
                }
        );
    }

    // create
    @Transactional
    public Employee createNewEmployee(Employee employee){
        return repository.save(employee);
    }

    // delete
    @Transactional
    public void removeEmployee(Integer employeeNumber){
        Employee employee = repository.findById(employeeNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Employee" + employeeNumber + "DOES NOT EXIST !!!")
        );
        repository.delete(employee);
    }

    // update
    @Transactional
    public Employee updateEmployee(Integer employeeNumber  , Employee employee){
        if (employee.getEmployeeNumber()!=null){
            if (!employee.getEmployeeNumber().equals(employeeNumber)){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                        "Conflict employee  !!! (" + employeeNumber + "vs" + employee.getEmployeeNumber() + ")");
            }
        }
        Employee existingEmployee = repository.findById(employeeNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Employee id + " + employeeNumber + "Does not exist!!")
        );
        return repository.save(employee);
    }
}
