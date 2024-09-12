package com.project1st.Service;

import com.project1st.DTO.MemberDto;
import com.project1st.DTO.NumberDto;
import com.project1st.Entity.MemberEntity;
import com.project1st.Entity.NumberEntity;
import com.project1st.Repository.MemberRepository;
import com.project1st.Repository.NumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

  private final MemberRepository memberRepository; // MemberService 객체 생성
  private final NumberRepository numberRepository;  // NumberRepository 객체 생성


  public List<NumberDto> getHistory( String userId ) {

    Pageable pageable = PageRequest.of( 0 , 10 );

    List<NumberDto> numberDtoList = new ArrayList<>();

    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    Long MemberId = memberEntity.getId();

    List<NumberEntity> numberEntityList = numberRepository.findByMemberEntity_IdOrderByDateDesc( MemberId , pageable );

    for( NumberEntity numberEntity : numberEntityList ){
      numberDtoList.add( NumberDto.of( numberEntity) );
    }

    return numberDtoList;
  }
}
