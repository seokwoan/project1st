package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "omok" )
public class OmokEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "omok_id" )
  private Long id;

  private Long win;

  private Long lose;

  private String nickName;

  @Enumerated( EnumType.STRING )
  private GameType gameType;

  private LocalDateTime date;


}
