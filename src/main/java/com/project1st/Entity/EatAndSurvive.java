package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "EatAndSurvive" )
public class EatAndSurvive extends PlayDate {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "EatAndSurvive_id" )
  private Long id;

  private Long score;

  @ManyToOne
  @JoinColumn( name = "member_id" )
  private MemberEntity memberEntity;

  @Enumerated( EnumType.STRING )
  private GameType gameType;

  @CreatedDate
  @Column( updatable = false )
  private LocalDateTime date;

}
