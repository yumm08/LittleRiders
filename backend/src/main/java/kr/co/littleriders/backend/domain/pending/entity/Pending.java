package kr.co.littleriders.backend.domain.pending.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "academy_child_allow_pending")
public class Pending {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// @ManyToOne
	// @JoinColumn(name = "academy_id", nullable = false)
	// private Academy academy;

	// @ManyToOne
	// @JoinColumn(name = "child_id", nullable = false)
	// private Child child;
}
