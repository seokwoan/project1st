package com.project1st.Control;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/game" )
public class GameControl {

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
  public String Number(){
    return "game/Number";
  }



}
