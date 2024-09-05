package com.project1st.Control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberControl {

    //로그인 페이지 요청
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "member/login";
    }

}
