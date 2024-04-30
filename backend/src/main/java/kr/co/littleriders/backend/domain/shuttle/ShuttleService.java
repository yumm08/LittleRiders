package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;

public interface ShuttleService {
    Shuttle findById(long id);
}
