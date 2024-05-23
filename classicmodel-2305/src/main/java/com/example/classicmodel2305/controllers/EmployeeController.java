package com.example.classicmodel2305.controllers;

import com.example.classicmodel2305.entities.Employee;
import com.example.classicmodel2305.entities.Office;
import com.example.classicmodel2305.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. rest controller
@RestController
// 2.path
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public Employee getEmployeeById(@PathVariable Integer employeeNumber) {
        return service.getEmployee(employeeNumber);
    }

    @PostMapping("")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return service.createNewEmployee(employee);
    }

    @PutMapping("/{employeeNumber}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeNumber) {
        return service.updateEmployee(employeeNumber , employee);
    }

    // delete void ! not return
    @DeleteMapping("/{employeeNumber}")
    public void removeEmployee (@PathVariable Integer employeeNumber) {
        service.removeEmployee(employeeNumber);
    }
}
