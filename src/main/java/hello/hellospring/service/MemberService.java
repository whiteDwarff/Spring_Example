package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 1. @Service 등록
//@Service // == @Component
public class MemberService {
    private final MemberRepository memberRepository;

    /*
      ! defendency injection
        - 같은 메모리의 객체를 Test 파일에서 공유할 수 있음
        - public으로 선언된 메서드를 Test 파일에서 실행

         2. memberRepository 의존성 주입
         -> memberService는 memberRepository가 필요하다 ~~
    */
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        valiedateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void valiedateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                // ifPresent : 이미 값이 있다면 ~~ 동작을 해 ~~, Optional을 감싸면 사용가능한 메서드
                // findName 메서드의 반환값은 Optional<Member> 이다.
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // 전체 회원 조회
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
