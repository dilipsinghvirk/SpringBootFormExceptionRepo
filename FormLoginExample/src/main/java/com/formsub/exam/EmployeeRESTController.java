package com.formsub.exam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRESTController {
	@PostMapping(value = "/employees")
	public ResponseEntity<EmployeeVO> addEmployee(@RequestBody EmployeeVO employee) {
		EmployeeDB.addEmployee(employee);
		return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") int id) {
		EmployeeVO employee = EmployeeDB.getEmployeeById(id);

		if (employee == null) {
			throw new RecordNotFoundException("Invalid employee id : " + id);
		}
		ResponseEntity<EmployeeVO> res =new  ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
		System.out.println("Response :"+res);
		return res;
	}
}
