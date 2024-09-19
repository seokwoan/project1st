package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.RememberEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class RememberDto {

  private Long id;

  private Long score;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public RememberEntity createEntity(){
    return mapper.map( this , RememberEntity.class );
  }

  public static RememberDto of(RememberEntity blackJackEntity ){
    return mapper.map( blackJackEntity , RememberDto.class );
  }



}
