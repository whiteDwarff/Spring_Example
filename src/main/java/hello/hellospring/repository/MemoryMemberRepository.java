package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    // 회원을 저장하는 메서드
    public Member save(Member member) {
        // member에 id에 1씩 증가되는 고유값 저장
        member.setId(++sequence);
        // store Map에 key value 형식으로 저장
        store.put(member.getId(), member);
        return member;
    }
    @Override
    // store Map에서 주어진 id에 해당하는 회원을 찾아서 반환
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
            1. store에 있는 값을 stream으로 변환
                - stream ?
                배열이나 컬렉션 등의 요소들에 대해 함수형 프로그래밍 스타일로 처리할 수 있는 기능
                요소들에 대해 매핑(mapping), 필터링(filtering), 정렬(sorting) 등의 작업을 보다 편리하게 할 수 있음
        */
        return store.values().stream()
                // 각 회원의 이름을 파라미터의 name과 비교하여 일치하는 회원을 필터링
                .filter(member -> member.getName().equals(name))
                // stream에서 첫번째로 찾은 요소를 반환
                .findAny();
    }

    @Override
    // store에 저장된 모든 회원을 리스트로 반환
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store의 모든 내용을 삭제
    public void clearStore() {
        store.clear();
    }
}
