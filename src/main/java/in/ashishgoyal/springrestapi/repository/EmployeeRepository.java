package in.ashishgoyal.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.ashishgoyal.springrestapi.model.Employee;




// JPA (Java Persistence API) - Default implementation is hybernate. Help to connect to database. 
// (ORM framework) Without writing SQL queries we can use java object to persist data to database.
// JPARepository provides database methods like CREATE, READ, UPDATE, DELETE. You can use those methods to perform database operations.
//JpaRepository<Employee, Long>  Employee class and we used Long because it is datatype of primary key in employee which is ID.


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	//Select * from employee where name = "ashish";
	List<Employee> findByName(String name);
	
	//Select * from employee where name = "ashish" and location = "pune";
	List<Employee> findByNameAndLocation(String name, String location);//JPA will convert above query automatically here

	//Select * from employees where name like "%ashish%";
	List<Employee> findByNameContaining(String keyword, Sort sort);
	
	
	
	List<Employee> findByCompanyName(String name);
	             //OR By Using JPQL Query
	@Query("FROM Employee where company.name = :name")
	List<Employee> getEmployeesByCompanyName(String name);
	
	
	// Example for JPQL Queries
	@Query("FROM Employee WHERE name = :name OR location = :location")
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
	
	@Modifying // When every we write JPQL queries for deleting, creating and updating we have to use this annotation other wise it will throw 500 server error in postman. For select queries this annotation not required.
	@Transactional // When every we write JPQL queries for deleting, creating and updating we have to use this annotation other wise it will throw 500 server error in postman. For select queries this annotation not required.
	@Query("DELETE FROM Employee WHERE name = :name")
	Integer deleteEmployeeByName(String name);
	
	
	
	
	
}
