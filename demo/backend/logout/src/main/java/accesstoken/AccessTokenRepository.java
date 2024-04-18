package accesstoken;

import java.util.ArrayList;
import java.util.List;

public class AccessTokenRepository {

    private static AccessTokenRepository instance;
    List<String> accessTokenList;

    private AccessTokenRepository() {
        accessTokenList = new ArrayList<String>();

    }

    public static AccessTokenRepository getInstance() {
        if (instance == null) {
            instance = new AccessTokenRepository();
        }
        return instance;
    }

    public void save(String accessToken) {
        accessTokenList.add(accessToken);
    }


    public boolean existsByToken(String token) {
        for (String t : accessTokenList) {
            if (t.equals(token)) {
                return true;
            }
        }
        return false;

    }
}
