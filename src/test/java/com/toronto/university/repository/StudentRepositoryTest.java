package com.toronto.university.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.toronto.university.entity.Guardian;
import com.toronto.university.entity.Student;


/**
 * 
 * @author jiaronghe
 * 
 * DataJpaTest: flush data
 * 
 * SpringBootTest: keep data in DB
 *
 */
//@DataJpaTest

@SpringBootTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository repository;

	@Test
	public void saveStudent() {
		Student student = Student.builder()
				.emailId("jiarong1213@gmail.com")
				.firstName("jiarong")
				.lastName("he")
				//.guardianName("roy")
				//.guardianEmail("jiaronghe1213@gmail.com")
				//.guardianMobile("1234567890")
				.build();
		
		repository.save(student);
	}
	
	@Test
	public void saveStudentWithGuardian() {
		Guardian guardian = Guardian.builder()
				.name("roy+1")
				.email("jiaronghe1213+1@gmail.com")
				.mobile("1234567890")
				.build();
		
		Student student = Student.builder()
				.emailId("jiarong1213+1@gmail.com")
				.firstName("jiarong+1")
				.lastName("he+1")
				.guardian(guardian)
				.build();
		
		repository.save(student);
	}
	
	@Test
	public void printAllStudent() {
		List<Student> list = repository.findAll();
		System.out.println("list: " + list);
	}
	
	@Test
	public void printStudentFindByFirstName() {
		List<Student> list = repository.findByFirstName("jiarong");
		System.out.println("list: " + list);
	}

	@Test
	public void printStudentFindByFirstNameContaining() {
		List<Student> list = repository.findByFirstNameContaining("jiarong");
		System.out.println("list: " + list);
	}
	
	@Test
	public void printStudentBasedOnGuardianName() {
		List<Student> list = repository.findByGuardianName("roy");
		System.out.println("list: "+ list);
	}
	
	@Test
	public void printStudentBasedOnEmaillAddr() {
		Student student = repository.getStudentByEmailAddress("jiarong1213@gmail.com");
		System.out.println("student: " + student);
	}
	
	@Test
	public void printStudentNameBasedOnEmaillAddr() {
		String firstName = repository.getStudentFirstNameByEmailAddress("jiarong1213@gmail.com");
		System.out.println("firstName: " + firstName);
	}
	
	@Test
	public void printStudentBasedOnEmaillAddrNative() {
		Student student = repository.getStudentByEmailAddressNative("jiarong1213@gmail.com");
		System.out.println("student: " + student);
	}
	
	@Test
	public void printStudentBasedOnEmaillAddrNativeNamedParam() {
		Student student = repository.getStudentByEmailAddressNativeNamedParam("jiarong1213@gmail.com");
		System.out.println("student: " + student);
	}
	
	@Test
	public void updateStudentNameByEmailId() {
		repository.updateStudentNameByEmailId("jiarong+updated", "jiarong1213@gmail.com");
		
	}
	
	
}
