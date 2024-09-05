package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.HitEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class HitDto {

  private Long id;

  private Long score;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public HitEntity createEntity(){
    return mapper.map( this , HitEntity.class );
  }

  public static HitDto of( HitEntity hitEntity ){
    return mapper.map( hitEntity , HitDto.class );
  }

}
