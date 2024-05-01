package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ShuttleDriveHistoryRepository extends CrudRepository<ShuttleDriveHistory,String> {

}
