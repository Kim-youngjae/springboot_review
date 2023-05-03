package com.yj.sbbasic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    public String index() {
        return "안녕하세요 영재가 만든 사이트에 오신것을 환영합니다.";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list"; // list로 리다이렉션 하도록 설정
    }

}
