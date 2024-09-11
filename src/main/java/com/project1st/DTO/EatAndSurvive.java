package com.project1st.DTO;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class EatAndSurvive {

  private Long id;

  private Long score;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public com.project1st.Entity.EatAndSurvive createEntity(){
    return mapper.map( this , com.project1st.Entity.EatAndSurvive.class );
  }

  public static EatAndSurvive of(com.project1st.Entity.EatAndSurvive eatAndSurvive){
    return mapper.map(eatAndSurvive, EatAndSurvive.class );
  }

}
