package in.ashishgoyal.springrestapi.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import in.ashishgoyal.springrestapi.request.EmployeeRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity // Entity Class : It is special type of class that represents the database table inside application
@Table(name="tbl_employee") // name of table in database

// Employee class is an entity class. 
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id") if param name is column name are same then @column annotation is not mandatory.
	private Long id;
	//@Column(name="password")if param name is column name are same then @column annotation is not mandatory.
	private String password;
	//@Column(name="name")if param name is column name are same then @column annotation is not mandatory.
	//@NotNull(message = "Name should not be null") // We can add "" in post body.
	//@NotEmpty(message = "Name should not be null") // "" not allowed in post body
	@NotBlank(message = "Name should not be null") // "     " to restrict entering blank characters values in post body.
	private String name;
	//@Column(name="age")if param name is column name are same then @column annotation is not mandatory.
	private Long age=0L;
	//@Column(name="location")if param name is column name are same then @column annotation is not mandatory.
	private String location;
	//@Column(name="email")if param name is column name are same then @column annotation is not mandatory.
	@Email(message = "please provide valid email address") //(Explore more this type of hybernate validator).
	private String email;
	//@Column(name="department")if param name is column name are same then @column annotation is not mandatory.
	@NotNull(message = "department should not be null")
	private List<String> department;
	@CreationTimestamp
	@Column(name="created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp 
	@Column(name="updated_at")
	private Date updateAt;
	
	@JoinColumn(name = "company_id")
	@OneToOne
	private Company company; // reference variable.
	
	public Employee(EmployeeRequest req ) {
		this.name = req.getName();
		this.department = req.getDepartment();
	}
	
	
	
}

