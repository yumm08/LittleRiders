package kr.co.littleriders.backend.domain.shuttleterminalattach.service;

import kr.co.littleriders.backend.domain.shuttleterminalattach.ShuttleTerminalAttachService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ShuttleTerminalAttachServiceImpl implements ShuttleTerminalAttachService {
    private final ShuttleTerminalAttachRepository shuttleTerminalAttachRepository;
}
