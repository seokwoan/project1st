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

    // 게임전적 히트 요청
    @GetMapping("/Point-Hit")
    public String PointHit(Model model) {
        return "/MyPage/GameRecord/Point-Hit";
    }

    // 게임전적 넘버 요청
    @GetMapping("/Point-Number")
    public String PointNumber(Model model) {
        return "/MyPage/GameRecord/Point-Number";
    }












}
