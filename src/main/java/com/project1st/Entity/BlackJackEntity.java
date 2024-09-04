package com.project1st.Entity;

import com.project1st.Constant.GameType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "blackjack")
public class BlackJackEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "blackjack_id" )
  private Long id;

  private Long win;

  private Long lose;

  @ManyToOne
  @JoinColumn( name = "member_id" )
  private MemberEntity memberEntity;

  @Enumerated( EnumType.STRING )
  private GameType gameType;

  private LocalDateTime date;

}
