package member;

public class Member {


    private int id;
    private String memberId;
    private String password;
    private String name;

    public Member(int id, String memberId, String password, String name) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
