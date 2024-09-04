package com.project1st.DTO;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

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

}
