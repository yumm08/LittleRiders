package kr.co.littleriders.backend.domain.terminal.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TerminalServiceImpl {
    private final TerminalRepository terminalRepository;
}
