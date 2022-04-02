package com.toronto.university.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toronto.university.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByFirstName(String name);

	public List<Student> findByFirstNameContaining(String name);
	
	public List<Student> findByLastNameNotNull();
	
	public List<Student> findByGuardianName(String name);
	
	public Student findByFirstNameAndLastName(String firstName, String lastName);
	
	
//	JPQL use class instead of DB table =======================
	
	@Query("select s from Student s where s.emailId = ?1")
	public Student getStudentByEmailAddress(String emailId);
	
	@Query("select s.firstName from Student s where s.emailId = ?1")
	public String getStudentFirstNameByEmailAddress(String emailId);
	
	
//	Native SQL =======================
	
	@Query(
			value = "SELECT * FROM tbl_student s where s.email_address = ?1",
			nativeQuery = true
			)
	public Student getStudentByEmailAddressNative(String emailId);
	
	@Query(
			value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
			nativeQuery = true
			)
	public Student getStudentByEmailAddressNativeNamedParam(
			@Param("emailId") String emailId
			);
	
	@Modifying
	@Transactional
	@Query(
			value = "UPDATE tbl_student set first_name = ?1 where email_address = ?2",
			nativeQuery = true
			)
	public int updateStudentNameByEmailId(
			String firstName,
			String emailId
			);
	
}
