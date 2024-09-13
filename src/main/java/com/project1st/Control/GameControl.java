package com.project1st.Control;

import com.project1st.Constant.GameType;
import com.project1st.DTO.MemberDto;
import com.project1st.Service.GameService;
import com.project1st.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/game" )
public class GameControl {

  private final MemberService memberService; // Principal 에서 가져온 아이디를 저장하기 위해 MemberService 객체 생성
  private final GameService gameService; // 게임 정보 저장을 위한 service

  // Bombvoid 맵핑
  @GetMapping( "/Bombvoid" )
  public String Bombvoid(){
    return "game/Bombvoid";
  }

  // EatAndSurvive 맵핑
  @GetMapping( "/EatAndSurvive" )
  public String eatAndSurvive(){
    return "game/EatAndSurvive";
  }


  // Blackjack 맵핑
  @GetMapping( "/BlackJack" )
  public String blackJack(){
    return "game/BlackJack";
  }


  // Number 맵핑
  @GetMapping( "/Number" )
  public String number( Principal principal , Model model ){
    String userId = principal.getName();
    MemberDto memberDto = memberService.getMemberInfo(userId);  // MemberService 정보 조회
    model.addAttribute("member", memberDto);
    return "game/Number";
  }

  // Number 점수 보내기
  @PostMapping( "/Number/{gameScore}" )
  public @ResponseBody ResponseEntity number(@PathVariable("gameScore") Long score , Principal principal ){
    gameService.scoreSave( score , principal.getName() , GameType.NUMBER );
    return new ResponseEntity<Long>( score , HttpStatus.OK );
  }

  // EatAndSurvive 점수 보내기
  @PostMapping( "/EatAndServive/{gameScore}" )
  public  @ResponseBody ResponseEntity eatAndSurvive( @PathVariable("gameScore") Long score , Principal principal ){
    gameService.scoreSave( score , principal.getName(), GameType.EATANDSURVIVE );
    return  new ResponseEntity<Long>( score , HttpStatus.OK );
  }

}
