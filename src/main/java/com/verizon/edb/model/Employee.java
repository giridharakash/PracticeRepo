package com.verizon.edb.model;

public class Employee {
	int employeeId;
	String name;
	double basic;
	double hra;
	String dept;
	
	public Employee() {}
	
	
	
	public Employee(int employeeId, String name, double basic, double hra, String dept) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.basic = basic;
		this.hra = hra;
		this.dept = dept;
	}



	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(double hra) {
		this.hra = hra;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
