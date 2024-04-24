package kr.co.littleriders.backend.global.response.api;

import lombok.Getter;

@Getter
public class LittleRidersErrorResponse {
    private String code;

    private LittleRidersErrorResponse(String code) {
        this.code = code;
    }

    public static LittleRidersErrorResponse from(String code){
        return new LittleRidersErrorResponse(code);
    }
}
