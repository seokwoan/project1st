package com.project1st.Service;

import com.project1st.DTO.MemberDto;
import com.project1st.Entity.MemberEntity;
import com.project1st.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 회원 가입폼의 내용을 데이터 베이스에 저장
    public void saveMember(@Valid MemberDto memberDto, PasswordEncoder passwordEncoder) {
        MemberEntity memberEntity = memberDto.createEntity(passwordEncoder);
        // 아이디와 이메일 중복여부
        validUserIdEmail(memberEntity);
        memberRepository.save(memberEntity);
    }

    private void validUserIdEmail(MemberEntity memberEntity) {
        MemberEntity find = memberRepository.findByUserId(memberEntity.getUserId());
        if(find != null) {
            throw new IllegalStateException("이미 가입된 아이디입니다!");
        }
        find = memberRepository.findByEmail(memberEntity.getEmail());
        if(find != null) {
            throw new IllegalStateException("이미 가입된 이메일입니다!");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인시 입력한 아이디로 계정 조회
        MemberEntity memberEntity = memberRepository.findByUserId(username);
        if(memberEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        // 입력한 비밀번호와 조회한 계정 비밀번호 비교를 위해 반환
        return User.builder()
                .username(memberEntity.getUserId())
                .password(memberEntity.getPassword()).build();

    }
}
