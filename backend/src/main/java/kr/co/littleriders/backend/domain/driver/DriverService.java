package kr.co.littleriders.backend.domain.driver;

import kr.co.littleriders.backend.domain.driver.entity.Driver;

public interface DriverService {

    Driver findById(long id);
    boolean existsById(long id);
    boolean notExistsById(long id);
	long save(Driver driver);
}
