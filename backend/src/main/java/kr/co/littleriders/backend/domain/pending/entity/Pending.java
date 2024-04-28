package kr.co.littleriders.backend.domain.pending.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PendingStatus status;

	private Pending(Academy academy, Child child, PendingStatus status) {
		this.academy = academy;
		this.child = child;
		this.status = status;
	}

	public static Pending of(Academy academy, Child child, PendingStatus status) {
		return new Pending(academy, child, status);
	}
}
