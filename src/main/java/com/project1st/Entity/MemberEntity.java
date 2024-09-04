package com.project1st.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "game_member" )
public class MemberEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( name = "member_id" )
  private Long id;

  @Column( unique = true )
  private String userId;

  private String password;

  @Column( unique = true )
  private String nickName;

  private String email;
}
