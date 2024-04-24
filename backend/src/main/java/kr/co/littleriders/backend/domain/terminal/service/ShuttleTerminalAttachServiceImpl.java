package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.ShuttleTerminalAttachService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ShuttleTerminalAttachServiceImpl implements ShuttleTerminalAttachService {
    private final ShuttleTerminalAttachRepository shuttleTerminalAttachRepository;
}
