package in.ashishgoyal.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashishgoyal.springrestapi.model.Company;
import in.ashishgoyal.springrestapi.model.Employee;
import in.ashishgoyal.springrestapi.repository.CompanyRepository;
import in.ashishgoyal.springrestapi.repository.EmployeeRepository;
import in.ashishgoyal.springrestapi.request.EmployeeRequest;
import in.ashishgoyal.springrestapi.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@Autowired
	private CompanyRepository cRepo;
		
	//localhost:8080/api/v1/employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long idl) {
		return new ResponseEntity<Employee>(eService.getSingleEmployee(idl), HttpStatus.OK);
	}
	
	//localhost:8090/employees?id=12
	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteEmployee (@RequestParam("id") Long id) {
		eService.deleteEmployee(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	/*
	 * @PostMapping("/employees") public ResponseEntity<Employee>
	 * saveEmployee(@Valid @RequestBody Employee employee) { return new
	 * ResponseEntity<Employee>(eService.saveEmployee(employee),
	 * HttpStatus.CREATED); }
	 */
	
	@PostMapping("/employees") 
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {
	Company c = new Company();
	c.setName(eRequest.getCompany());
	c = cRepo.save(c);
	
	Employee employee = new Employee(eRequest);
	employee.setCompany(c);
	
	employee = eRepo.save(employee);
	
	return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	
	}
	
	
	@PostMapping("/employees/department") 
	public ResponseEntity<String> saveEmployeeDepartment(@Valid @RequestBody EmployeeRequest eRequest) {
	Employee employee = new Employee(eRequest);
	employee = eRepo.save(employee);
	
	for(String s : eRequest.getDepartment()) {
		Company c = new Company();
		c.setName(s);
		c.setEmployee(employee);
		
		cRepo.save(c);
	}
	return new ResponseEntity<String>("Record Saved successfully", HttpStatus.CREATED);
	
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee> (eService.updateEmployee(employee), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByKeyword")
	public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name), HttpStatus.OK);
	}
	

	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeesByNameORLocation(@PathVariable String name, @PathVariable String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameORLocation(name, location), HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeesByName(@PathVariable String name){
		return new ResponseEntity<String>(eService.deleteByEmployeeName(name)+ " No. of Records Deleted", HttpStatus.OK);
	}
	
	@GetMapping("/employees/filter/{name}")
	public ResponseEntity<List<Employee>> getEmployeesByCompany(@PathVariable String name) {
		//return new ResponseEntity<List<Employee>>(eRepo.findByCompanyName(name), HttpStatus.OK);
	return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByCompanyName(name), HttpStatus.OK);
	}
	
	
}