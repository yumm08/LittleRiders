package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;

public interface BoardDropHistoryService {

    public BoardDropHistory findById(Long id);



    public boolean existsById(Long id);
    public boolean notExistsById(Long id);
}
