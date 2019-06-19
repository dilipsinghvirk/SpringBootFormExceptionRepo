package com.formsub.exam;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StudentResource {

	public static final Logger logger = org.slf4j.LoggerFactory.getLogger(StudentResource.class);

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		logger.info("Inside getAll Method:");
		List<Student> list = studentRepository.findAll();
		logger.info("Size" + list.size());
		
		return list;
	}

	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);
		return student.get();
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}

	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		ResponseEntity<Object> res = ResponseEntity.created(location).build();
		
		return res;

	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);
		Student std = new Student();
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		std.setId(id);

		studentRepository.save(std);
		ResponseEntity<Object> response = ResponseEntity.noContent().build();
		return response;
	}

}
