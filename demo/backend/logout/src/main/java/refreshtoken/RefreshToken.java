package refreshtoken;

public class RefreshToken {
    long memberId; //member record에 대한 pk 입니다.
    String token;

    public RefreshToken(long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getToken() {
        return token;
    }
}
