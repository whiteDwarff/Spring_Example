package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
* @Controller 사용 시
* Spring container에 controller를 넣어서 Spring이 관리하게 됨 ( bean )
* */
@Controller
public class MemberController {
    /*
     * - service는 container에서 받아서 사용해야 함
     * - 기존에 사용하던 생성자의 개념이 아님 !!
     * - private final MemberService memberService = new MemberService(); XX!!
     * - @Autowired 사용 시 Spring container에 있는 service를 연결 시켜줌
     * - wired : 연결하다
     * - 연관관계 등록 !!!!
     *
     * main 메서드에 포함된 root 패키지의 하위 패키지 및 클레스는 스프링 빈으로 등록되지만
       하위 패지키지가 아닌 것들은 컴포넌트 스캔의 대상이 되지 않는다.
     * */
    private final MemberService memberService;

    // Defendency Injection 의존성 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        // redirect:/ : root 화면으로 리다이렉션 시킴
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}