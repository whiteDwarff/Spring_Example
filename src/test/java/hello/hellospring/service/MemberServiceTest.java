package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// command + shift + t
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void befoeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given ~가 주어졌을 떄
        Member member = new Member();
        member.setName("hello");
        // when ~가 실행했을 때
        Long saveId = memberService.join(member);
        // then 나오는 결과
        Member findMember = memberService.findOne(saveId).get();
        // static import
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring1");

        // when
        memberService.join(member1);
        // then
        /*
           실행 순서
           1.  memberService.join(member2) 를 실행할건데 ~
           2.  IllegalStateException.class 예외처리가 실행되야 해~
         */
        // command + option + v : 할당 문법을 자동완성 해준다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
          Member member = new Member();
          Long result = memberService.join(member);
          System.out.println(result);
          assertThat(result).isEqualTo(2);
    }
}