package com.verizon.edb.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.edb.model.Employee;
import com.verizon.edb.service.IEmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeAPI {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> listEmployeeAction() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeAction(@PathVariable("employeeId") int employeeId) {
		ResponseEntity<Employee> resp = null;
		Employee b = employeeService.getById(employeeId);
		if (b == null)
			resp = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<Employee>(b, HttpStatus.OK);

		return resp;
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Employee> deleteEmployeeAction(@PathVariable("employeeId") int employeeId) {
		boolean isDeleted = employeeService.deleteById(employeeId);
		ResponseEntity<Employee> resp = null;
		if (!isDeleted)
			resp = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<Employee>(HttpStatus.OK);

		return resp;
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployeeAction(@RequestBody Employee emp) {
		ResponseEntity<Employee> resp = null;
		emp = employeeService.add(emp);
		if (emp != null)
			resp = new ResponseEntity<>(emp, HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}

	@PutMapping
	public ResponseEntity<Employee> updateEmployeeAction(@RequestBody Employee emp) {
		ResponseEntity<Employee> resp = null;
		if (employeeService.getById(emp.getEmployeeId()) == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			emp = employeeService.update(emp);

			if (emp != null)
				resp = new ResponseEntity<>(emp, HttpStatus.OK);
			else
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

}
