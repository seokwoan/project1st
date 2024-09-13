package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.BombvoidEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class BombvoidDto {

  private Long id;

  private Long win;

  private Long lose;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public BombvoidEntity createEntity(){
    return mapper.map( this , BombvoidEntity.class );
  }

  public static BombvoidDto of(BombvoidEntity bombvoidEntity){
    return mapper.map(bombvoidEntity, BombvoidDto.class );
  }
}
