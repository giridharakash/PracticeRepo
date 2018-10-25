package com.verizon.edb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.edb.model.Employee;
import com.verizon.edb.restapi.EmployeeAPI;
import com.verizon.edb.service.EmployeeServiceImpl;
import com.verizon.edb.util.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeAPI.class)
public class EmployeeAPITest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private EmployeeServiceImpl empServiceMock;



	@Before
	public void setUp() throws Exception {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
		mockMvc = null;
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployee()).thenReturn(empList);

		mockMvc.perform(get("/employees")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.empServiceMock).isNotNull();
		int empId = 101;
	

		Employee empAdded = new Employee();

		empAdded.setEmployeeId(101);
		empAdded.setName("Akash");
		empAdded.setBasic(15000);
		empAdded.setHra(15);
		empAdded.setDept("Sales");

		when(empServiceMock.getById(empId)).thenReturn(empAdded);

		mockMvc.perform(get("/employees/101")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testAddEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();

		emp.setName("Hari");
		emp.setBasic(13000);
		emp.setHra(25);
		emp.setDept("Operations");

		Employee empAdded = new Employee();
		empAdded.setEmployeeId(102);
		empAdded.setName("Hari");
		empAdded.setBasic(13000);
		empAdded.setHra(25);
		empAdded.setDept("Operations");

		System.out.println(emp);

		when(empServiceMock.add(Mockito.any(Employee.class))).thenReturn(empAdded);

		mockMvc.perform(post("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();
		emp.setEmployeeId(103);
		emp.setName("Viswa");
		emp.setBasic(10000);
		emp.setHra(35);
		emp.setDept("Network");

		int empId = 103;
		

		Employee empAdded = new Employee();

		empAdded.setEmployeeId(103);
		empAdded.setName("Viswa");
		empAdded.setBasic(10000);
		empAdded.setHra(35);
		empAdded.setDept("HR");

		when(empServiceMock.getById(empId)).thenReturn(empAdded);

		when(empServiceMock.update(Mockito.any(Employee.class))).thenReturn(emp);

		mockMvc.perform(put("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));


	}

	@Test
	public void testDeleteEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		int empId = 103;

		when(empServiceMock.deleteById(empId)).thenReturn(true);

		mockMvc.perform(delete("/employees/103")).andExpect(status().isOk()).andDo(print());

	}

}


