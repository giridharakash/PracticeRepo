package com.verizon.edb.service;

import java.util.List;

import com.verizon.edb.model.Employee;

public interface IEmployeeService {
	Employee add(Employee emp);
	Employee update(Employee emp);
	Employee getById(int employeeId);
	boolean deleteById(int employeeId);
	List<Employee> getAllEmployee();
}
