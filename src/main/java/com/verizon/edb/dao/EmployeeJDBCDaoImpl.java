package com.verizon.edb.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.verizon.edb.model.Employee;
@Repository
public class EmployeeJDBCDaoImpl implements IEmployeeDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public EmployeeJDBCDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	@Override
	public Employee add(Employee emp) {
		int count = jdbcTemplate.update(IQueryMapper.INS_EMPLOYEE_QRY, emp.getEmployeeId(), emp.getName(), emp.getBasic(),
				emp.getHra(), emp.getDept());
		if (count < 1) {
			emp = null;
		}
		return emp;

	}

	@Override
	public Employee update(Employee emp) {
		int count = jdbcTemplate.update(IQueryMapper.UPDATE_EMPLOYEE_QRY, emp.getName(), emp.getBasic(),
				emp.getHra(), emp.getDept(), emp.getEmployeeId());
		if (count < 1) {
			emp = null;
		}
		return emp;
	}

	@Override
	public Employee getById(int employeeId) {
		List<Employee> employees = jdbcTemplate.query(IQueryMapper.GET_EMPLOYEE_QRY, new Object[] { employeeId }, new EmployeeRowMapper());
		Employee emp = null;
		if (employees != null && employees.size() == 1) {
			emp = employees.get(0);
		}
		return emp;
	}

	@Override
	public boolean deleteById(int employeeId) {
		boolean isDeleted = false;
		int count = jdbcTemplate.update(IQueryMapper.DEL_EMPLOYEE_QRY, employeeId);
		if (count >= 1) {
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employees = jdbcTemplate.query(IQueryMapper.GET_ALL_EMPLOYEES_QRY, new EmployeeRowMapper());
		return employees;
	}

}
