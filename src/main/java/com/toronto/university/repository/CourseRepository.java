package com.toronto.university.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toronto.university.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByTitleContaining(String title, Pageable pageable);
}
