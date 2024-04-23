package kr.co.littleriders.backend.domain.childboarddropinfo.service;


import kr.co.littleriders.backend.domain.childboarddropinfo.ChildBoardDropInfoService;
import kr.co.littleriders.backend.domain.childboarddropinfo.entity.ChildBoardDropInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ChildBoardDropInfoServiceServiceImpl implements ChildBoardDropInfoService {

    private final ChildBoardDropInfoRepository childBoardDropInfoRepository;


    @Override
    public ChildBoardDropInfo findById(Long id) {
        return childBoardDropInfoRepository.findById(id).orElseThrow(
                RuntimeException::new //TODO: Exception 변경
        );
    }

    @Override
    public boolean existsById(Long id) {
        return childBoardDropInfoRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !childBoardDropInfoRepository.existsById(id);
    }
}
