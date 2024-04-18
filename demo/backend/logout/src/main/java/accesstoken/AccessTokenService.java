package accesstoken;

public class AccessTokenService {

    private static AccessTokenService instance;
    private AccessTokenRepository accessTokenRepository;

    private AccessTokenService() {
        accessTokenRepository = AccessTokenRepository.getInstance();
    }

    public static AccessTokenService getInstance() {
        if(instance == null){
            instance = new AccessTokenService();
        }

        return instance;
    }

    public void blockAccessToken(String token){
        accessTokenRepository.save(token);
    }

    public boolean isBlockedAccessToken(String token) {
        return accessTokenRepository.existsByToken(token);
    }



}
