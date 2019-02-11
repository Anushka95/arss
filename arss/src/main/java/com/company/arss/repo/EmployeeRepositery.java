package com.company.arss.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.arss.model.Employee;

public interface EmployeeRepositery extends JpaRepository<Employee, Long> {
}
