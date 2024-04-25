package kr.co.littleriders.backend.global.jwt;

import kr.co.littleriders.backend.global.entity.MemberType;
import lombok.Getter;


@Getter
public class JwtReIssue {
    private final long id;

    private final MemberType memberType;

    private JwtReIssue(long id, MemberType memberType) {
        this.id = id;
        this.memberType = memberType;
    }

    public static JwtReIssue of(long id, MemberType memberType) {
        return new JwtReIssue(id, memberType);
    }
}
