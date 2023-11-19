package hello.hellospring.domain;
import javax.persistence.*;

/*
* JPA : 객체와 테이블간의 매핑
*
* @Entity  : JPA를 사용하려면 어노테이션 매핑 필수, JPA가 관리한다 ~
* IDENTITY : DB가 값을 자동으로 생성해주는 것, PK
*
* */
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // DB 테이블의 컬럼명을 매핑할 수 있다.
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/*
*
* */