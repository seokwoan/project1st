package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.EatAndSurviveEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class EatAndSurviveDto {
  // 이름수정

  private Long id;

  private Long score;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public EatAndSurviveEntity createEntity(){
    return mapper.map( this , EatAndSurviveEntity.class );
  }

  public static EatAndSurviveDto of(EatAndSurviveEntity eatAndSurviveEntity){
    return mapper.map( eatAndSurviveEntity , EatAndSurviveDto.class );
  }

}
