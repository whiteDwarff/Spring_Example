package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // Optional: Null이 반환될 경우 Optional 으로 감싸서 Null을 반환
    // 저장된 회원을 찾아오는 기능
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    // 저장된 회원 리스트를 모두 반환
    List<Member> findAll();
}
