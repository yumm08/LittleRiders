package kr.co.littleriders.backend.domain.history.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import kr.co.littleriders.backend.domain.common.Gender;

@Entity
@Table(name = "child_history")
public class ChildHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "child_id", nullable = false)
	private Child child;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "birth_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "image_path")
	private String imagePath;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

}
