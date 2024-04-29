package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.TerminalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;
}
