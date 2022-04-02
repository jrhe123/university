package com.toronto.university.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.toronto.university.entity.Course;
import com.toronto.university.entity.CourseMaterial;
import com.toronto.university.entity.Student;
import com.toronto.university.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest {

	@Autowired
	private CourseMaterialRepository repository;
	
	@Autowired
	private CourseRepository repository2;
	
	@Test
	public void saveCourseMaterial() {
		
		Course course = Course.builder()
				.title("JAVA")
				.credit(6)
				.build();
		
		CourseMaterial material = CourseMaterial.builder()
				.url("www.google.ca")
				.course(course)
				.build();
		
		repository.save(material);
	}
	
	@Test
	public void printAllCourseMaterials() {
		List<CourseMaterial> list = repository.findAll();
		System.out.println("list: " + list);
	}
	
	@Test
	public void printAllCourses() {
		List<Course> list = repository2.findAll();
		System.out.println("list: " + list);
	}
	
	@Test
	public void saveCourseWithTeacher() {
		
		Teacher teacher = Teacher.builder()
				.firstName("teacherF")
				.lastName("teachterL")
				.build();
		
		Course course = Course.builder()
				.title("Ruby")
				.credit(2)
				.teacher(teacher)
				.build();
		
		repository2.save(course);
	}
	
	@Test
	public void findAllPagination() {
		Pageable firstPage = PageRequest.of(0, 3);
		Pageable secondPage = PageRequest.of(1, 2);
		
		List<Course> list = repository2.findAll(firstPage).getContent();
		long totalRecord = repository2.findAll(firstPage).getTotalElements();
		long totalPage = repository2.findAll(firstPage).getTotalPages();
		
		System.out.println("totalRecord: " + totalRecord);
		System.out.println("totalPage: " + totalPage);
		
		System.out.println("list: " + list.size());
	}
	
	@Test
	public void findAllPaginationSorting() {
		Pageable sortByTitle = PageRequest.of(0, 3, Sort.by("title"));
		Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
		Pageable sortByTitleAndCrditDesc = PageRequest.of(0, 2, 
				Sort.by("title").descending().and(Sort.by(("credit"))));
		
		List<Course> list = repository2.findAll(sortByTitleAndCrditDesc).getContent();
		System.out.println("list: " + list);
	}
	
	@Test
	public void findTitleContainingPagination() {
		Pageable firstPage = PageRequest.of(0, 3);
		List<Course> list = repository2.findByTitleContaining("A", firstPage);
		
		System.out.println("list: " + list);
	}
	
	@Test
	public void saveCourseWithStudentAndTeacher() {
		
		Teacher teacher = Teacher.builder()
				.firstName("AI")
				.lastName("Machine")
				.build();
		
		Student student = Student.builder()
				.firstName("gg")
				.lastName("ff")
				.emailId("roytest@gmail.com")
				.build();
		
		Course course = Course.builder()
				.title("AI")
				.credit(6)
				.teacher(teacher)
				.build();
		course.addStudents(student);
		
		repository2.save(course);
	}

}
