package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "Hit" )
public class HitEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "hit_id" )
  private Long id;

  private Long score;

  @ManyToOne
  @JoinColumn( name = "member_id" )
  private MemberEntity memberEntity;

  @Enumerated( EnumType.STRING )
  private GameType gameType;

  private LocalDateTime date;

}
