package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocationHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ShuttleLocationHistoryRepository extends CrudRepository<ShuttleLocationHistory, Long> {
    List<ShuttleLocationHistory> findAllByShuttleId(Long shuttleId);

    Optional<ShuttleLocationHistory> findByShuttleId(long shuttleId);

    boolean existsByShuttleId(long shuttleId);
}
