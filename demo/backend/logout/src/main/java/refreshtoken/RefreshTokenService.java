package refreshtoken;

public class RefreshTokenService {

    private static RefreshTokenService instance;

    private RefreshTokenRepository refreshTokenRepository;

    private RefreshTokenService() {
        refreshTokenRepository = RefreshTokenRepository.getInstance();
    }
    public static RefreshTokenService getInstance(){
        if(instance == null){
            instance = new RefreshTokenService();
        }
        return instance;
    }


    public void whiteRefreshToken(long memberId, String token){
        RefreshToken refreshToken = new RefreshToken(memberId,token);
        refreshTokenRepository.save(refreshToken);
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
    public boolean isWhiteRefreshToken(String token){
       return refreshTokenRepository.existsByToken(token);
    }
}
