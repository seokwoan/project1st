package com.project1st.Control;

import com.project1st.DTO.MemberDto;
import com.project1st.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.lang.reflect.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberControl {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final View error;



    //로그인 페이지 요청
    @GetMapping ("/login")
    public String loginPage(Model model) {
        return "member/login";
    }

    // 회원가입 페이지 요청
    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    //회원가입 요청(저장)
    @PostMapping("/join")
    public String join(@Valid MemberDto memberDto,
                       BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "member/join";
        }
        try {
            memberService.saveMember(memberDto, passwordEncoder);
        }catch (IllegalStateException e1){
            bindingResult.rejectValue("userId","error.memberDto", e1.getMessage());
            return "member/join";
        }catch(IllegalArgumentException e2){
            bindingResult.rejectValue("email","error.memberDto", e2.getMessage());
            return "member/join";
        }
        return "redirect:/member/login";
    }

    // 로그인 실패 - 아이디 or 비밀번호 틀린 경우
    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginFailMsg",
                "아이디 또는 비밀번호가 올바르지 않습니다.");
        return "member/login";
    }


}
