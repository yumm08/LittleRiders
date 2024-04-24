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
	private Long id; // 보호자 이력 id

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "family_id", nullable = false)
	private Family family; // 보호자

	@Column(name = "name", nullable = false)
	private String name; // 성명

	@Column(name = "address", nullable = false)
	private String address; // 주소

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber; // 전화번호

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt; // 생성일자
}
