package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.NumberEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class NumberDto {

  private Long id;

  private Long win;

  private Long lose;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public NumberEntity createEntity(){
    return mapper.map( this , NumberEntity.class );
  }

  public static NumberDto of( NumberEntity numberEntity ){
    return mapper.map( numberEntity , NumberDto.class );
  }
}
