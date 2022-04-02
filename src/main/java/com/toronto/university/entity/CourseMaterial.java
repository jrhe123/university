package com.toronto.university.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.el.parser.AstFalse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
@Table(name = "tbl_course_material")
public class CourseMaterial {

	@Id
	@SequenceGenerator(
			name = "course_material_squence",
			sequenceName = "course_material_squence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_material_squence"
			)
	private Long courseMaterialId;
	
	private String url;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, // when you need course, you need to specify
			// fetch = FetchType.EAGER // always fetch course as well
			optional = false // must have material when we save course, if optional is false
			)
	@JoinColumn(
			name = "course_id",
			referencedColumnName = "courseId"
			)
	private Course course;
}
