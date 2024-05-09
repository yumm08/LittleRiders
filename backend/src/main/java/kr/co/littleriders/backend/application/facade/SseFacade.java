package kr.co.littleriders.backend.application.facade;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseFacade {

    SseEmitter createAcademySseConnectionByAcademyId(long academyId);
}