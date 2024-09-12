package com.project1st.Control;


import com.project1st.DTO.MemberDto;
import com.project1st.DTO.NumberDto;
import com.project1st.Service.MemberService;
import com.project1st.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/MyPage")
public class MyPage {

    private final MemberService memberService;  // MemberService 추가
    private final MyPageService myPageService; // MyPageService 추가

    //마이페이지 요청
    @GetMapping("/MyPage")
    public String myPage(Model model) {
        return "/MyPage/MyPage";
    }

    // 회원정보 요청 (회원 정보를 MyPage에서 조회 및 출력)
    @GetMapping("/myInfo")
    public String showMemberInfo(Model model, Principal principal) {
        // 로그인된 사용자의 아이디로 회원 정보 조회
        String userId = principal.getName();  // SecurityContext에서 현재 로그인된 사용자의 아이디를 가져옴
        MemberDto memberDto = memberService.getMemberInfo(userId);  // MemberService로 정보 조회
        model.addAttribute("member", memberDto);  // 회원 정보를 모델에 추가
        return "/MyPage/MyInfo";  // myInfo.html 템플릿으로 이동
    }






    // 게임전적 오목페이지 요청
    @GetMapping("/Point-Omok")
    public String PointOmok(Model model) {
        return "/MyPage/GameRecord/Point-Omok";
    }

    // 게임전적 블랙잭 요청
    @GetMapping("/Point-BlackJack")
    public String PointBlackJack(Model model) {
        return "/MyPage/GameRecord/Point-BlackJack";
    }

    // 게임전적 EatAndSurvive 요청
    @GetMapping("/Point-EatAndSurvive")
    public String PointHit(Model model) {
        return "/MyPage/GameRecord/Point-EatAndSurvive";
    }

    // 게임전적 넘버 요청
    @GetMapping("/Point-Number")
    public String PointNumber( Model model , Principal principal ) {
        List<NumberDto> numberDtoList = myPageService.getHistory( principal.getName() );
        model.addAttribute( "history" , numberDtoList );

        return "/MyPage/GameRecord/Point-Number";
    }


}