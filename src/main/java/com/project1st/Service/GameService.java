package com.project1st.Service;

import com.project1st.DTO.NumberDto;
import com.project1st.Entity.MemberEntity;
import com.project1st.Entity.NumberEntity;
import com.project1st.Repository.MemberRepository;
import com.project1st.Repository.NumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

  private final MemberRepository memberRepository; // Member 정보 가져오기위해
  private final NumberRepository numberRepository; // Number repository 객체 생성

  public void scoreSave( Long score , String userId) {
    NumberEntity numberEntity = new NumberEntity();
    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    numberEntity.setId( memberEntity.getId() );
    numberEntity.setScore( score );
    numberRepository.save( numberEntity );
  }
}
