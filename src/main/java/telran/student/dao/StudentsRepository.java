package telran.student.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.student.entity.Student;
import telran.student.interfaces.IStudents;

@Repository
public class StudentsRepository implements IStudents {
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public boolean addStudent(Student student) {
		try {
			em.persist(student);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean removeStudent(String email) {
		Student student = getStudentById(email);
		if (student != null) {
			em.remove(student);
			return true;
		}
		return false;
	}

	@Override
	public Iterable<Student> getAllStudents() {
		TypedQuery<Student> query = em.createQuery("select s from Student s",Student.class);
		return query.getResultList();
	}

	@Override
	public Iterable<Student> getStudentsByName(String name) {
		TypedQuery<Student> query = em.createQuery("select s from Student s where s.name=?1",Student.class);
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	public Iterable<Student> getStudentsByRoom(int min, int max) {
		TypedQuery<Student> query = em.createQuery("select s from Student s where s.room between ?1 and ?2",Student.class);
		query.setParameter(1, min);
		query.setParameter(2, max);
		return query.getResultList();
	}

	@Override
	public Student getStudentById(String email) {
		Student student = em.find(Student.class, email);
		return student;
	}

	@Override
	@Transactional
	public boolean editStudent(Student student) {
		if (getStudentById(student.getEmail()) != null) {
			em.merge(student);
			return true;
		}
		return false;
	}

}
