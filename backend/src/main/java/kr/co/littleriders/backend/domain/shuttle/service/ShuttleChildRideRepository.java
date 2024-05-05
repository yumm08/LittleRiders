package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleChildRide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ShuttleChildRideRepository extends CrudRepository<ShuttleChildRide, Long> {

    Optional<ShuttleChildRide> findByShuttleId(long shuttleId);

}
