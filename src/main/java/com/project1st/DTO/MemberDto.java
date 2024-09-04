package com.project1st.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

  private Long id;

  @NotBlank( message = "아이디를 입력해 주세요" )
  private String userId;

  private String password;

  @NotBlank( message = "닉네임은 필수 입니다" )
  private String nickName;

  private String email;

}
