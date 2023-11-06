package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    // response의 body에 데이터를 직접 넣겠다 ~
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // JSON API 응답 방식
    @GetMapping("hello-api")
    // JSON 반환 방식이 default
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        // command + n 단축키
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
