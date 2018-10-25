package com.verizon.edb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.verizon.edb.dao.IEmployeeDao;
import com.verizon.edb.model.Employee;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private IEmployeeDao empDao;
	
	@Override
	public Employee add(Employee emp) {
		return empDao.add(emp);
	}

	@Override
	public Employee update(Employee emp) {
		return empDao.update(emp);
	}

	@Override
	public Employee getById(int employeeId) {
		return empDao.getById(employeeId);
	}

	@Override
	public boolean deleteById(int employeeId) {
		return empDao.deleteById(employeeId);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return empDao.getAllEmployee();
	}

}
