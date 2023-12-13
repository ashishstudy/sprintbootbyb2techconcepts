package in.ashishgoyal.springrestapi.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
	
	private String name;
	private List<String> department;
	private String company;

}
