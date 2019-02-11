package com.company.arss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.arss.dao.EmployeeDao;
import com.company.arss.model.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	// -----------------------------------------------------------------------------------------------------------------
	@GetMapping("/emp")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("empList");
		List<Employee> empList = employeeDao.findAll();
		model.addObject("empList", empList);

		return model;
	}

	@GetMapping("/addEmp")
	public ModelAndView addEmp() {
		ModelAndView model = new ModelAndView();
		Employee employee = new Employee();
		model.addObject("empForm", employee);
		model.setViewName("empForm");
		return model;
	}

	@PostMapping("/saveEmp")
	public ModelAndView saveEmp(@ModelAttribute("empForm") Employee employee) {
		String errormsg = "";
		String name = employee.getName();
		String designation = employee.getDesignation();
		String experties = employee.getExperties();

		ModelAndView model = new ModelAndView();
		if (name.equalsIgnoreCase("")) {
			if (errormsg.equalsIgnoreCase("")) {
				errormsg = "Please ";
			}
			errormsg += "Enter Name.. ";
		}
		if (designation.equalsIgnoreCase("")) {
			if (errormsg.equalsIgnoreCase("")) {
				errormsg = "Please ";
			}
			errormsg += "Enter Designation.. ";
		}
		if (experties.equalsIgnoreCase("")) {
			if (errormsg.equalsIgnoreCase("")) {
				errormsg = "Please ";
			}
			errormsg += "Enter Experties.. ";
		}
		if (!errormsg.equalsIgnoreCase("")) {
			model.addObject("empForm", employee);
			model.addObject("errormsg", errormsg);
			model.setViewName("empForm");
		} else {
			employeeDao.save(employee);
			model = new ModelAndView("redirect:/company/emp");
		}
		System.out.println("error: " + errormsg);
		return model;
	}

	@GetMapping("/updateEmp/{id}")
	public ModelAndView updateEmp(@PathVariable(value = "id") Long empId) {
		ModelAndView model = new ModelAndView();

		Employee employee = employeeDao.findById(empId);
		model.addObject("empForm", employee);
		model.setViewName("empForm");

		return model;
	}

	@GetMapping("/removeEmp/{id}")
	public ModelAndView delete(@PathVariable(value = "id") Long empId) {
		Employee employee = employeeDao.findById(empId);
		employeeDao.delete(employee);

		return new ModelAndView("redirect:/company/emp");
	}

	// -----------------------------------------------------------------------------------------------------------------
	@GetMapping("/createEmployees/{id}")
	public ResponseEntity<List<Employee>> createEmployees(@PathVariable(value = "id") Long empId) {
		List<Employee> employeeList = null;

		System.out.println("-----------------> IN createEmployees");

		employeeList = createEmps();
		System.out.println("-----------------> After createEmps");
		if (employeeList == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(employeeList);
	}

	public List<Employee> createEmps() {
		List<Employee> employeeList = new ArrayList<>();
		System.out.println("-----------------> IN createEmps");

		// file read
		File file = new File("/root/Projects/arss/src/main/resources/a.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				// \\s+ means any number of whitespaces between tokens
				//String[] tokens = line.split("\\s+");
				String[] tokens = line.split("\"\\s+\"");
				String name = tokens[0].replaceAll("\"","");
				String designation = tokens[1].replaceAll("\"","");
				String experties = tokens[2].replaceAll("\"","");

				Employee employee = new Employee();
				employee.setName(name);
				employee.setDesignation(designation);
				employee.setExperties(experties);
				employeeList.add(employee);

				System.out.println("name:        " + name + "\ndesignation: " + designation + "\nexperties:   "
						+ experties + "\n-------------------------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int i = -1;
		for (i = 0; i < employeeList.size(); i++) {
			Employee employee = employeeList.get(i);
			employeeDao.save(employee);
		}
		if (i != 0 && i == employeeList.size()) {
			System.out.println("Saved Successfully " + i + " Users");
		}

		return employeeList;
	}
	// -----------------------------------------------------------------------------------------------------------------

	/* save an employee */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeDao.save(employee);
	}

	/* get all employees */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}

	/* get an employee by id */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long empId) {
		Employee employee = employeeDao.findById(empId);

		if (employee == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(employee);
	}

	/* update an employee by id */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable(value = "id") Long empId,
			@Valid @RequestBody Employee empDetails) {
		Employee employee = employeeDao.findById(empId);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}

		employee.setName(empDetails.getName());
		employee.setDesignation(empDetails.getDesignation());
		employee.setExperties(empDetails.getExperties());

		Employee updateEmployee = employeeDao.save(employee);

		return ResponseEntity.ok().body(updateEmployee);
	}

	/* delete an employee */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empId) {
		Employee employee = employeeDao.findById(empId);

		if (employee == null) {
			return ResponseEntity.notFound().build();
		}

		employeeDao.delete(employee);
		return ResponseEntity.ok().build();
	}
}
