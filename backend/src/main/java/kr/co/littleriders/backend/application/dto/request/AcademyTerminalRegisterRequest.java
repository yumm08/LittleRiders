package kr.co.littleriders.backend.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AcademyTerminalRegisterRequest {

    @NotBlank
    private final String terminalNumber;


    public Terminal toTerminal(Academy academy){
        return Terminal.of(academy,terminalNumber);
    }

}
