package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;

public interface ShuttleDriveHistoryService {

    ShuttleDriveHistory findById(String id);
    String save(ShuttleDriveHistory shuttleDriveHistory);
}
