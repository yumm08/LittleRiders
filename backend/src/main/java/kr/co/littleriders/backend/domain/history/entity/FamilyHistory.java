package kr.co.littleriders.backend.domain.history.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.family.entity.Family;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity @Getter
@Table(name = "family_history")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt; // 생성일자
}
