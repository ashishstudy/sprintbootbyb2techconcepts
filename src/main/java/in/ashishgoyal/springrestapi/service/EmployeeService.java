package in.ashishgoyal.springrestapi.service;

import java.util.List;

import in.ashishgoyal.springrestapi.model.Employee;

public interface EmployeeService {
	
	
	List<Employee> getEmployees(int pageNumber, int pageSize);

	Employee saveEmployee (Employee employee);
	
	Employee getSingleEmployee(Long id);
	
	void deleteEmployee(Long id);
	
	Employee updateEmployee(Employee employee);
	
	List<Employee> getEmployeesByName(String name);
	
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
	
	List<Employee> getEmployeesByKeyword(String name);
	
	List<Employee> getEmployeesByNameORLocation(String name, String location);
	
	Integer deleteByEmployeeName(String name);
}
