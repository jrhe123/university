package com.toronto.university.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.toronto.university.entity.Course;
import com.toronto.university.entity.Teacher;

@SpringBootTest
class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository repository;


	@Test
	public void saveTeacher() {
		Course course = Course.builder()
				.title("PYTHON")
				.credit(3)
				.build();
		Course course2 = Course.builder()
				.title("GO")
				.credit(4)
				.build();
		List<Course> list = new ArrayList<>();
		list.add(course);
		list.add(course2);
		
		Teacher teacher = Teacher.builder()
				.firstName("roytest-2")
				.lastName("hetest-2")
				// .courses(list)
				.build();
		
		repository.save(teacher);
	}
}
