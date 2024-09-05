package com.project1st.DTO;

import com.project1st.Constant.GameType;
import com.project1st.Entity.OmokEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class OmokDto {

  private Long id;

  private Long win;

  private Long lose;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

  private static ModelMapper mapper = new ModelMapper();

  public OmokEntity createEntity(){
    return mapper.map( this , OmokEntity.class );
  }

  public static OmokDto of( OmokEntity omokEntity ){
    return mapper.map( omokEntity , OmokDto.class );
  }
}
