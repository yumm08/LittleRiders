package kr.co.littleriders.backend.domain.pending.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "academy_child_allow_pending")
@NoArgsConstructor
public class Pending {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; // 승인 id

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_id", nullable = false)
	private Academy academy; // 학원

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "child_id", nullable = false)
	private Child child; // 자녀

	private Pending(Academy academy, Child child) {
		this.academy = academy;
		this.child = child;
	}

	public static Pending of(Academy academy, Child child) {
		return new Pending(academy, child);
	}
}
