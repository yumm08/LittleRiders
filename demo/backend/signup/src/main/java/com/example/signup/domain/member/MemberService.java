package com.example.signup.domain.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException()
        );
    }

    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    public void save(Member member){
        memberRepository.save(member);
    }

}
