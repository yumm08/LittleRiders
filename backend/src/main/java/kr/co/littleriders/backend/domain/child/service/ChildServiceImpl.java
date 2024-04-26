package kr.co.littleriders.backend.domain.child.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ChildServiceImpl implements ChildService {

	private final ChildRepository childRepository;

	@Override
	public Long save(Child child) {
		return childRepository.save(child).getId();
	}

	@Override
	public List<Child> findByFamilyId(Long familyId) {
		return childRepository.findByFamilyId(familyId);
	}
}
