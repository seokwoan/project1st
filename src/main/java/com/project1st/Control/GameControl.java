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

  @GetMapping( "/omok" )
  public String omok(){
    return "game/omok";
  }


}
