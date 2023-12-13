package in.ashishgoyal.springrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import in.ashishgoyal.springrestapi.model.Employee;
import in.ashishgoyal.springrestapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	// Hard Coded values
	/*private static List<Employee> list = new ArrayList<>();
	
	 * static { Employee e = new Employee(); e.setName("Ashish"); e.setAge(30L);
	 * e.setDepartment("QA"); e.setEmail("ashishg120"); e.setLocation("Pune");
	 * e.setPassword("test123"); list.add(e);
	 * 
	 * e = new Employee(); e.setName("Shivansh"); e.setAge(1L);
	 * e.setDepartment("Devloper"); e.setEmail("shivanshg120");
	 * e.setLocation("Pune"); e.setPassword("test1234"); list.add(e); }
	 */
	
	
	// Values from database
	@Autowired
	private EmployeeRepository eRepository;
	
	

	@Override
	public List<Employee> getEmployees(int pageNumber, int pageSize) {
		Pageable pages = PageRequest.of(pageNumber, pageSize, Direction.DESC, "id");
		return eRepository.findAll(pages).getContent();
		//return eRepository.findAll();  // It will return list of employees
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
		
	}



	@Override
	public Employee getSingleEmployee(Long id) {
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Employee is not found for the id : " + id);
	}



	@Override
	public void deleteEmployee(Long id) {
	  eRepository.deleteById(id);
	}



	@Override
	public Employee updateEmployee(Employee employee) {
		return eRepository.save(employee);
	}



	@Override
	public List<Employee> getEmployeesByName(String name) {
		
		return eRepository.findByName(name);
	}



	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		
		return eRepository.findByNameAndLocation(name, location);
	}



	@Override
	public List<Employee> getEmployeesByKeyword(String name) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return eRepository.findByNameContaining(name, sort);
	}



	@Override
	public List<Employee> getEmployeesByNameORLocation(String name, String location) {
		return eRepository.getEmployeesByNameAndLocation(name, location);
	}



	@Override
	public Integer deleteByEmployeeName(String name) {
		
		return eRepository.deleteEmployeeByName(name);
	}

}
