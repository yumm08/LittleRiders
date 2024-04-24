package kr.co.littleriders.backend.domain.academy.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "academy_child")
public class AcademyChild {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "child_id", nullable = false)
	 private Child child;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "academy_family_id", nullable = false)
	 private AcademyFamily academyFamily;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "academy_id", nullable = false)
	 private Academy academy;

	@OneToMany(mappedBy = "academyChild")
	private List<ChildBoardDropInfo> childBoardDropInfoList;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private AcademyChildStatus status;

	@Column(name = "card_type", nullable = false)
	private CardType cardType;

	@Column(name = "card_number")
	private String cardNumber;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
