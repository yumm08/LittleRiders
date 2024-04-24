package kr.co.littleriders.backend.domain.history.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.family.entity.Family;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "family_history")
public class FamilyHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "family_id", nullable = false)
	private Family family;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
}
