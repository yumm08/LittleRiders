package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;

public interface ShuttleLocationService {


    ShuttleLocation findByShuttleId(long shuttleId);
    void save(ShuttleLocation shuttleLocation);

    void delete(ShuttleLocation shuttleLocation);
}
