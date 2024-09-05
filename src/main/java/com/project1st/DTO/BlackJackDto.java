package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.BlackJackEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlackJackDto {

  private Long id;

  private Long win;

  private Long lose;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public BlackJackEntity createEntity(){
    return mapper.map( this , BlackJackEntity.class );
  }

  public static BlackJackDto of( BlackJackEntity blackJackEntity ){
    return mapper.map( blackJackEntity , BlackJackDto.class );
  }



}
