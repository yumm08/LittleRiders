package kr.co.littleriders.backend.domain.boarddrophistory;

import kr.co.littleriders.backend.domain.boarddrophistory.entity.BoardDropHistory;
import kr.co.littleriders.backend.domain.route.entity.Route;

public interface BoardDropHistoryService {

    public BoardDropHistory findById(Long id);



    public boolean existsById(Long id);
    public boolean notExistsById(Long id);
}
