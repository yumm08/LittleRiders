package refreshtoken;

import java.util.ArrayList;
import java.util.List;

public class RefreshTokenRepository {
    private static RefreshTokenRepository instance;

    private final List<RefreshToken> refreshTokenList;

    private RefreshTokenRepository() {
        refreshTokenList = new ArrayList<RefreshToken>();

    }

    public static RefreshTokenRepository getInstance() {
        if (instance == null) {
            instance = new RefreshTokenRepository();
        }
        return instance;
    }



    public void save(RefreshToken refreshToken) {
        for (RefreshToken rt : refreshTokenList) {
            if (rt.getMemberId() == refreshToken.getMemberId()) {
                refreshTokenList.remove(rt);
                break;
            }
        }

        refreshTokenList.add(refreshToken);

    }


    public boolean existsByToken(String token) {
        for (RefreshToken rt : refreshTokenList) {
            if (rt.getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public void deleteByToken(String token) {
        for (RefreshToken rt : refreshTokenList) {
            if (rt.getToken().equals(token)) {
                refreshTokenList.remove(rt);
                return;
            }
        }
    }


}
