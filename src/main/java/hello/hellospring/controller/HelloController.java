package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // GetMapping, 괄호 안은 URL을 넣어줘야 함
    @GetMapping("hello")
    public String hello(Model model) {
        // addAttribute: key, value 형식으로 데이터를 전송 가능
        model.addAttribute("data", "Spring!!!");
        // return type은 페이지 단위 (/resources/templates/hello.html)
        return "hello";
    }
}
