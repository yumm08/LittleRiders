package kr.co.littleriders.backend.domain.history.service;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.history.FamilyHistoryService;
import kr.co.littleriders.backend.domain.history.entity.FamilyHistory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FamilyHistoryServiceImpl implements FamilyHistoryService {

	private final FamilyHistoryRepository familyHistoryRepository;

	@Override
	public FamilyHistory findByCreatedAt(AcademyFamily academyFamily) {
		return familyHistoryRepository.findByFamilyAndCreatedAtBeforeOrderByCreatedAtDesc(academyFamily.getFamily(), academyFamily.getUpdatedAt());
	}

	@Override
	public long save(FamilyHistory familyHistory) {
		return familyHistoryRepository.save(familyHistory).getId();
	}
}
