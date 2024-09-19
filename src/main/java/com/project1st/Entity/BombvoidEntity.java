package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "Bombvoid" )
public class BombvoidEntity extends PlayDate {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "Bombvoid_id" )
  private Long id;

  private Long score;

  @ManyToOne
  @JoinColumn( name = "member_id" )
  private MemberEntity memberEntity;

  @Enumerated( EnumType.STRING )
  private GameType gameType;

}
