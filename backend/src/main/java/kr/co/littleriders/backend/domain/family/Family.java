package kr.co.littleriders.backend.domain.family;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Family {

	@Id
	private Long id;

	private String email;

	private String password;

	private String name;

	private String phoneNumber;
}
