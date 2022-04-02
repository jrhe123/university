package com.toronto.university.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_teacher")
public class Teacher {

	@Id
	@SequenceGenerator(
			name = "teacher_squence",
			sequenceName = "teacher_squence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "teacher_squence"
			)
	private Long teacherId;
	
	private String firstName;
	
	private String lastName;
	
	
//	notes: use ManyToOne instead
//	
//	@OneToMany(
//			cascade = CascadeType.ALL
//			)
//	@JoinColumn(
//			name = "teacher_id",
//			referencedColumnName = "teacherId"
//			)
//	private List<Course> courses;
}
