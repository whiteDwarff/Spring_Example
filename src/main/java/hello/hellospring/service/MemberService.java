package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //
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
