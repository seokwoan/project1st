package com.project1st.Control;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/MyPage")
public class MyPage {

    //마이페이지 요청
    @GetMapping("/MyPage")
    public String myPage(Model model) {
        return "MyPage/MyPage";
    }




}
