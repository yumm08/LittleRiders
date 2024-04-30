package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;

public interface AcademyTerminalFacade {
    void registerTerminal(AuthAcademy authAcademy, AcademyTerminalRegisterRequest academyTerminalRegisterRequest);
}
