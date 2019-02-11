package com.company.arss.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.arss.model.Employee;
import com.company.arss.repo.EmployeeRepositery;

@Service
public class EmployeeDao {

	@Autowired
	EmployeeRepositery employeeRepositery;
	
	/* save an employee */
	public Employee save(Employee employee) {
		return employeeRepositery.save(employee);		
	}
	
	/* search all employees */
	public List<Employee> findAll(){
		return employeeRepositery.findAll();
	}
	
	/* get an employee by id */
	public Employee findById(Long empId) {
		return employeeRepositery.findById(empId).orElse(null);
	}
	
	/* delete an employee by id */
	public void delete(Employee employee) {
		employeeRepositery.delete(employee);
	}
	
}
