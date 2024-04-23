package kr.co.littleriders.backend.domain.childboarddropinfo;

import kr.co.littleriders.backend.domain.childboarddropinfo.entity.ChildBoardDropInfo;

public interface ChildBoardDropInfoService {

    ChildBoardDropInfo findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

}
