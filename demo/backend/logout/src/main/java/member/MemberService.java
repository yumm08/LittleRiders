package member;

import jwt.Jwt;
import jwt.JwtService;

public class MemberService {
    private final MemberStorage memberStorage;

    private static MemberService instance;

    private MemberService() {
        memberStorage = MemberStorage.getInstance();

    }

    public static MemberService getInstance() {


        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }


    public void register(int id, String memberId, String password, String name) {
        Member member = new Member(id, memberId, password, name);
        memberStorage.save(member);
    }

    public Member findByMemberIdAndPassword(String memberId, String password) {
        return memberStorage.findByMemberIdAndPassword(memberId, password);
    }


}
