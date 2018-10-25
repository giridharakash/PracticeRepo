package com.verizon.edb.dao;

public interface IQueryMapper {

	public static final String INS_EMPLOYEE_QRY = "INSERT INTO employees values(?,?,?,?,?)";
	public static final String DEL_EMPLOYEE_QRY = "DELETE FROM employees WHERE empId=?";
	public static final String UPDATE_EMPLOYEE_QRY = "UPDATE employees SET name=?,basic=?,hra=?,dept=? WHERE empId=?";
	public static final String GET_EMPLOYEE_QRY = "SELECT * FROM employees WHERE empId=?";
	public static final String GET_ALL_EMPLOYEES_QRY = "SELECT * FROM employees";
}
