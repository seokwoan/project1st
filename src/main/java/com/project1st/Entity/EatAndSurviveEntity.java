package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "EatAndSurvive" )
public class EatAndSurviveEntity extends PlayDate {
  // 이름수정

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

}
