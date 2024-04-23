package kr.co.littleriders.backend.domain.terminal.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class TerminalServiceImpl {
    private final TerminalRepository terminalRepository;
}
