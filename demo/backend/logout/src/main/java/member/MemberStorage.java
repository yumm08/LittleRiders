package member;

import java.util.ArrayList;
import java.util.List;

public class MemberStorage {

    private final List<Member> memberList;

    private static MemberStorage instance;
    private MemberStorage() {
        this.memberList = new ArrayList<Member>();
    }

    public static MemberStorage getInstance() {
        if(instance == null){
            instance = new MemberStorage();
        }
        return instance;
    }

    public void save(Member member){
        this.memberList.add(member);
    }
    public Member findById(int id){

        for(Member m : this.memberList){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public Member findByMemberIdAndPassword(String memberId, String password){
        for(Member m : this.memberList){
            if(m.getMemberId().equals(memberId) && m.getPassword().equals(password)){
                return m;
            }
        }
        return null;
    }
}
