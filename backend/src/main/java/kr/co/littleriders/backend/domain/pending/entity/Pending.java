package kr.co.littleriders.backend.domain.pending.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;

@Entity
@Table(name = "academy_child_allow_pending")
public class Pending {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "academy_id", nullable = false)
	 private Academy academy;

	// @ManyToOne
	// @JoinColumn(name = "child_id", nullable = false)
	// private Child child;
}
