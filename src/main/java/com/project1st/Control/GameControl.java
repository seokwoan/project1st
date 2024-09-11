package com.project1st.Control;

import com.project1st.DTO.MemberDto;
import com.project1st.DTO.NumberDto;
import com.project1st.Service.GameService;
import com.project1st.Service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/game" )
public class GameControl {

  private final MemberService memberService; // Principal 에서 가져온 아이디를 저장하기 위해 MemberService 객체 생성
  private final GameService gameService; // 게임 정보 저장을 위한 service

  // Omok 맵핑
  @GetMapping( "/omok" )
  public String omok(){
    return "game/omok";
  }

  // Hit 맵핑
  @GetMapping( "/Hit" )
  public String Hit(){
    return "game/Hit";
  }


  // Blackjack 맵핑
  @GetMapping( "/BlackJack" )
  public String BlackJack(){
    return "game/BlackJack";
  }


  // Number 맵핑
  @GetMapping( "/Number" )
  public String Number( Principal principal , Model model ){
    String userId = principal.getName();
    MemberDto memberDto = memberService.getMemberInfo(userId);  // MemberService 정보 조회
    model.addAttribute("member", memberDto);
    return "game/Number";
  }

  // Number 점수 보내기
  @PostMapping( "/Number" )
  public void Number( NumberDto numberDto ){
    gameService.scoreSave( numberDto );
  }



}
