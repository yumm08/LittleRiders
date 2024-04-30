package kr.co.littleriders.backend.global.auth.dto;

import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.Getter;

@Getter
public class CodeTerminal implements CodeDTO {

    private final long terminalId;
    private final long shuttleId;

    private CodeTerminal(long terminalId, long shuttleId) {
        this.terminalId = terminalId;
        this.shuttleId = shuttleId;
    }
    public static CodeTerminal of (Terminal terminal, Shuttle shuttle){
        return new CodeTerminal(terminal.getId(),shuttle.getId());
    }
}
