package com.project1st.Service;

import com.project1st.Constant.GameType;
import com.project1st.DTO.*;
import com.project1st.Entity.*;
import com.project1st.Repository.*;
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
  private final RememberRepository rememberRepository;
  private final BombvoidRepository bombvoidRepository;
  private final EatAndSurviveRepository eatAndSurviveRepository;


  public List<NumberDto> getNumHistory( String userId ) {

    Pageable pageable = PageRequest.of( 0 , 10 );

    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    Long MemberId = memberEntity.getId();

    List<NumberDto> numberDtoList = new ArrayList<>();

    List<NumberEntity> numberEntityList = numberRepository.findByMemberEntity_IdOrderByDateDesc(MemberId, pageable);

    for (NumberEntity numberEntity : numberEntityList) {
      numberDtoList.add(NumberDto.of(numberEntity));
    }
    return numberDtoList;
  }


  public List<RememberDto> getReHistory( String userId ) {
    Pageable pageable = PageRequest.of( 0 , 10 );

    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    Long MemberId = memberEntity.getId();

    List<RememberDto> rememberDtoList = new ArrayList<>();

    List<RememberEntity> rememberEntityList = rememberRepository.findByMemberEntity_IdOrderByDateDesc(MemberId, pageable);

    for (RememberEntity rememberEntity : rememberEntityList) {
      rememberDtoList.add(RememberDto.of(rememberEntity));
    }
    return rememberDtoList;
  }

  public List<BombvoidDto> getBombHistory( String userId ) {
    Pageable pageable = PageRequest.of( 0 , 10 );

    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    Long MemberId = memberEntity.getId();

    List<BombvoidDto> bombvoidDtoList = new ArrayList<>();

    List<BombvoidEntity> bombvoidEntityList = bombvoidRepository.findByMemberEntity_IdOrderByDateDesc(MemberId, pageable);

    for (BombvoidEntity bombvoidEntity : bombvoidEntityList ) {
      bombvoidDtoList.add(BombvoidDto.of(bombvoidEntity));
    }
    return bombvoidDtoList;
  }

  public List<EatAndSurviveDto> getHitHistory( String userId ) {
    Pageable pageable = PageRequest.of( 0 , 10 );

    MemberEntity memberEntity = memberRepository.findByUserId( userId );
    Long MemberId = memberEntity.getId();

    List<EatAndSurviveDto> eatAndSurviveDtoList = new ArrayList<>();

    List<EatAndSurviveEntity> eatAndSurviveEntityList = eatAndSurviveRepository.findByMemberEntity_IdOrderByDateDesc(MemberId, pageable);

    for (EatAndSurviveEntity eatAndSurviveEntity : eatAndSurviveEntityList) {
      eatAndSurviveDtoList.add(EatAndSurviveDto.of(eatAndSurviveEntity));
    }
    return eatAndSurviveDtoList;
  }
}
