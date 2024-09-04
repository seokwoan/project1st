package com.project1st.DTO;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HitDto {

  private Long id;

  private Long score;

  private String nickName;

  private GameType gameType;

  private LocalDateTime date;

}
