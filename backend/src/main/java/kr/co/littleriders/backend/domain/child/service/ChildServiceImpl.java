package kr.co.littleriders.backend.domain.child.service;

import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.exception.ChildException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ChildServiceImpl implements ChildService {

	private final ChildRepository childRepository;

	@Override
	public long save(Child child) {
		return childRepository.save(child).getId();
	}

	@Override
	public Child findById(long childId) {
		return childRepository.findById(childId).orElseThrow(
				() -> ChildException.from(ChildErrorCode.NOT_FOUND)
		);
	}

	@Override
	public List<Child> findByFamilyId(long familyId) {
		return childRepository.findByFamilyId(familyId);
	}
}
