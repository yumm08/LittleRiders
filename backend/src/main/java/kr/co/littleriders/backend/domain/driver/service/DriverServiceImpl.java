package kr.co.littleriders.backend.domain.driver.service;

import kr.co.littleriders.backend.domain.driver.DriverService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
}
