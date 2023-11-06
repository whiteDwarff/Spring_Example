package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝나면 store를 삭제할 수 있다.
    // 테스트는 의존관계로 실행되면 안된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // Optional 클래스의 메소드로, Optional 객체의 값이 존재할 경우 값을 반환
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spirng1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spirng2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
