package kr.co.littleriders.backend.global.auth;


import jakarta.servlet.http.HttpServletRequest;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.ShuttleTerminalAttachException;
import kr.co.littleriders.backend.global.auth.annotation.Code;
import kr.co.littleriders.backend.global.auth.dto.CodeDTO;
import kr.co.littleriders.backend.global.auth.dto.CodeTerminal;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class CodeArgumentResolver implements HandlerMethodArgumentResolver {

    private final TerminalService terminalService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameter().getType();
        return parameter.hasParameterAnnotation(Code.class) && CodeDTO.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {




        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest == null) {
            throw AuthException.from(AuthErrorCode.UUID_NOT_FOUND);
        }

        String uuid = httpServletRequest.getHeader("Authorization");
        if (uuid == null) {
            throw AuthException.from(AuthErrorCode.UUID_NOT_FOUND);
        }

        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        //uuid 정규식
        if(!UUID_REGEX.matcher(uuid).matches()){
            throw AuthException.from(AuthErrorCode.UUID_NOT_VALID);
        }

        Terminal terminal = terminalService.findByTerminalNumber(uuid);
        ShuttleTerminalAttach shuttleTerminalAttach = terminal.getShuttleTerminalAttach();
        if(shuttleTerminalAttach == null){
            throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
        }
        Shuttle shuttle = terminal.getShuttleTerminalAttach().getShuttle();
        return CodeTerminal.of(terminal,shuttle);

    }
}
