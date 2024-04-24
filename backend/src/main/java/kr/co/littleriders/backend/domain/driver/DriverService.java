package kr.co.littleriders.backend.domain.driver;

import kr.co.littleriders.backend.domain.driver.entity.Driver;

public interface DriverService {

    Driver findById(Long id);
    boolean existsById(Long id);
    boolean notExistsById(Long id);
}
