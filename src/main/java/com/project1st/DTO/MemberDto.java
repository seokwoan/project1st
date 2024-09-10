package com.project1st.DTO;

import com.project1st.Entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto {

  private Long id;

  @NotBlank( message = "아이디를 입력해 주세요" )
  private String userId;

  @Size(min=4 , max=12, message="비밀번호는 4~12자리 입니다.")
  private String password;

  @NotBlank( message = "닉네임은 필수 입니다" )
  private String nickName;

  @NotBlank(message = "이메일을 작성해주세요")
  private String email;



  // DTO -> Entity
  public MemberEntity createEntity( PasswordEncoder passwordEncoder ){
    MemberEntity memberEntity = new MemberEntity();

    memberEntity.setUserId( this.userId );
    memberEntity.setEmail( this.email );
    memberEntity.setNickName( this.nickName );

    String pw = passwordEncoder.encode( this.password );
    memberEntity.setPassword( pw );

    return memberEntity;
  }

  // Entity -> DTO
  public static MemberDto of( MemberEntity memberEntity ){
    MemberDto memberDto = new MemberDto();

    memberDto.setUserId( memberEntity.getUserId() );
    memberDto.setNickName( memberEntity.getNickName() );
    memberDto.setEmail( memberEntity.getEmail() );

    return memberDto;
  }

}
