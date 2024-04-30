package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;

import java.util.List;

public interface ShuttleLocationHistoryService {

    List<ShuttleLocationHistory> findAllByShuttleId(Long shuttleId);

    void save(ShuttleLocationHistory location);

    void delete(ShuttleLocationHistory location);
}
