package telran.student.interfaces;

import telran.student.entity.Student;

public interface IStudents {
	boolean addStudent(Student student);
	boolean removeStudent(String email);
	Iterable<Student> getAllStudents();
	Iterable<Student> getStudentsByName(String name);
	Iterable<Student> getStudentsByRoom(int min, int max);
	Student getStudentById(String email);
	boolean editStudent(Student student);
	
}
