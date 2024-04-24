package kr.co.littleriders.backend.domain.family.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;

import java.util.List;

@Entity
@Table(name = "family")
public class Family {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; // 보호자 id

	@Column(name = "email", nullable = false)
	private String email; // 이메일

	@Column(name = "password", nullable = false)
	private String password; // 비밀번호

	@Column(name = "name", nullable = false)
	private String name; // 성명

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber; // 연락처

	@OneToMany(mappedBy = "family")
	private List<Child> child; // 자녀 목록

}
