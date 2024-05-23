package com.example.classicmodel2305.repositories;

import com.example.classicmodel2305.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
}
