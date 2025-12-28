package employeeservice;

import java.util.List;
import java.util.NoSuchElementException;

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

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("employee")
public class EmployeeController {
	@Autowired 
	private EmployeeService employeeService;

	// Retrieve operation (retrieving all employees)
	@GetMapping    //("/api/Employees") // used to retrieve resource from server.
	public List<Employee> listAll() {
		return employeeService.listAll();
	}

	// Retrieve Operation (retrieving employee by id)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {
		try {
			Employee employee = employeeService.get(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

	}

	// Create Operation
	 @PostMapping
	    public ResponseEntity<?> add(@RequestBody Employee employee) {
		 employeeService.save(employee);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }


	// Update Operation
	 @PutMapping("/{id}") 
	 public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer id) {
	     try {
	         Employee existingEmployee = employeeService.get(id);
	         existingEmployee.setName(employee.getName());
	         existingEmployee.setPosition(employee.getPosition());
	         existingEmployee.setSalary(employee.getSalary());
	         // Assuming you don't want to change the ID, so we don't set it
	         employeeService.save(existingEmployee);

	         return new ResponseEntity<>(HttpStatus.OK);
	     } catch (NoSuchElementException e) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }

	// Delete Operation // It Maps the HTTP DELETE requests
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
        	employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
