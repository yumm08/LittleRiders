import accesstoken.AccessTokenService;
import jwt.Jwt;
import jwt.JwtService;
import member.Member;
import member.MemberService;
import member.MemberStorage;
import refreshtoken.RefreshToken;
import refreshtoken.RefreshTokenService;

import java.util.List;

public class Main {

    static MemberService memberService = MemberService.getInstance();
    static RefreshTokenService refreshTokenService = RefreshTokenService.getInstance();
    static AccessTokenService accessTokenService = AccessTokenService.getInstance();
    static JwtService jwtService = JwtService.getInstance();

    public static void main(String[] args){

        //회원가입 진행
        MemberService memberService = MemberService.getInstance();

        memberService.register(1,"kim","kimpassword","김가네");
        memberService.register(2,"lee","leepassword","이가네");
        memberService.register(3,"park","parkpassword","박가네");
        memberService.register(4,"choi","choipassword","최가네");
        memberService.register(5,"hong","hongpassword","홍가네");


        Jwt firstLogin = login("kim","kimpassword");


        callSomeAPI(firstLogin.getAccessToken()); //정상적으로 호출되는 경우

        reCreateAccessTokenAndRefreshToken(firstLogin.getRefreshToken()); //정상적으로 액세스, 리프레시 재발급

        reCreateAccessTokenAndRefreshToken(firstLogin.getRefreshToken()); //한번 사용한 리프레시 또 쓰는경우

        Jwt secondLogin = login("kim","kimpassword");
        System.out.println("===로그아웃을 하지 않고 또 로그인을 하는 경우 기존 액세스 토큰은?===");
        callSomeAPI(firstLogin.getAccessToken());
        callSomeAPI(secondLogin.getAccessToken());

        System.out.println("=== 로그아웃을 하고 api 요청 하는 경우와 재발급 요청 하는 경우 ===");
        logout(secondLogin.getAccessToken(), secondLogin.getRefreshToken());
        callSomeAPI(secondLogin.getAccessToken());
        reCreateAccessTokenAndRefreshToken(secondLogin.getRefreshToken());










    }

    public static Jwt login(String memberId, String password){
        //로그인을 하면 다음과 같은 일이 일어난다.

        Member member = memberService.findByMemberIdAndPassword(memberId,password); //1 유저조회

        Jwt jwt = jwtService.createAccessAndRefreshToken(member.getId()); //2 유저 정보를 기준으로 엑세스, 리프레시 생성

        refreshTokenService.whiteRefreshToken(member.getId(), jwt.getRefreshToken()); // 리프레시토큰을 화이트리스트 처리
        // 리프레시토큰이 있나 보는거임
        //redis 를 써서 화이트리스트의 유효시간을 설정하면 좋다. expired time 까지 redis 에 저장하면 되니깐
        return jwt;
    }

    public static void logout(String accessToken, String refreshToken){
        //로그아웃을 하면 기존 accessToken 과 refreshToken 을 만료시킨다.

        accessTokenService.blockAccessToken(accessToken); //얘는 db 에 추가 -> 블랙리스트 정책
        refreshTokenService.deleteRefreshToken(refreshToken);//얘는 db 에서 삭제 -> 화이트리스트 정책
    }

    public static void callSomeAPI(String accessToken){
        //accessToken 은 expired time 안이라면 무조건 ok 임

        if(accessTokenService.isBlockedAccessToken(accessToken)){ //db 에 `있으면` 오류
            System.out.println("로그아웃 된거에요");
            return;
        }
        System.out.println("API 요청 성공!");
    }

    public static void reCreateAccessTokenAndRefreshToken(String refreshToken){
        if(!refreshTokenService.isWhiteRefreshToken(refreshToken)){ //db 에 없으면 오류
            System.out.println("유효하지 않은거 같은 리프래시 인데??");
            return;
        }

        System.out.println("리프레시 토큰을 재 발급 할게요!");
        refreshTokenService.deleteRefreshToken(refreshToken); //원래 토큰 만료!
    }


}
