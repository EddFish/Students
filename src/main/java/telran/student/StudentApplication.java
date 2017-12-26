package telran.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.student.entity.Student;
import telran.student.interfaces.IStudents;
import telran.student.interfaces.StudentsConstants;

@SpringBootApplication
@RestController
public class StudentApplication {
	@Autowired
	IStudents students;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
	@PostMapping(StudentsConstants.STUDENT)
	@CrossOrigin
	public boolean addStudent(@RequestBody Student student) {
		return students.addStudent(student);
	}
	
	@DeleteMapping(StudentsConstants.STUDENT)
	@CrossOrigin
	public boolean removeStudent(@RequestParam String email) {
		return students.removeStudent(email);
	}
	
	@PutMapping(StudentsConstants.STUDENT)
	@CrossOrigin
	public boolean updateStudent(@RequestBody Student student) {
		return students.editStudent(student);
	}
	
	@GetMapping(StudentsConstants.STUDENT)
	@CrossOrigin
	public Student getStudent(@RequestParam String email) {
		return students.getStudentById(email);
	}
	
	@GetMapping(StudentsConstants.STUDENTS)
	@CrossOrigin
	public Iterable<Student> getStudents() {
		return students.getAllStudents();
	}
	
	@GetMapping(StudentsConstants.NAME)
	@CrossOrigin
	public Iterable<Student> getStudents(@RequestParam String name) {
		return students.getStudentsByName(name);
	}
	
	@GetMapping(StudentsConstants.ROOM)
	@CrossOrigin
	public Iterable<Student> getStudents(@RequestParam int min, @RequestParam int max) {
		return students.getStudentsByRoom(min, max);
	}
	
	
}
