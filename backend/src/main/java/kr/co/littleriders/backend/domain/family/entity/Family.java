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
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@OneToMany(mappedBy = "family")
	private List<Child> child;

}
