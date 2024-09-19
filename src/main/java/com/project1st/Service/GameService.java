package com.project1st.Service;

import com.project1st.Constant.GameType;
import com.project1st.Entity.BombvoidEntity;
import com.project1st.Entity.EatAndSurviveEntity;
import com.project1st.Entity.NumberEntity;
import com.project1st.Entity.RememberEntity;
import com.project1st.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

  private final MemberRepository memberRepository; // Member 정보 가져오기위해
  private final NumberRepository numberRepository; // Number repository 객체 생성
  private final EatAndSurviveRepository eatAndSurviveRepository;  // EatAndSurvive repository 객체 생성
  private final RememberRepository rememberRepository; // remember repository 객체 생성
  private final BombvoidRepository bombvoidRepository; // bombvoid repository 객체 생성

  public void scoreSave( Long score , String userId , GameType gameType ) {

    switch ( gameType ) {
      case NUMBER :
        NumberEntity numberEntity = new NumberEntity();
        numberEntity.setMemberEntity( memberRepository.findByUserId( userId ) );
        numberEntity.setScore( score );
        numberEntity.setGameType( gameType );
        numberRepository.save( numberEntity );
        return;
      case EATANDSURVIVE :
        EatAndSurviveEntity eatAndSurviveEntity = new EatAndSurviveEntity();
        eatAndSurviveEntity.setMemberEntity( memberRepository.findByUserId( userId ) );
        eatAndSurviveEntity.setScore( score );
        eatAndSurviveEntity.setGameType( gameType );
        eatAndSurviveRepository.save( eatAndSurviveEntity );
        return;
      case REMEMBER :
        RememberEntity  rememberEntity = new RememberEntity();
        rememberEntity.setMemberEntity( memberRepository.findByUserId( userId ) );
        rememberEntity.setScore( score );
        rememberEntity.setGameType( gameType );
        rememberRepository.save( rememberEntity );
        return;
      case BOMBVOID :
        BombvoidEntity bombvoidEntity = new BombvoidEntity();
        bombvoidEntity.setMemberEntity( memberRepository.findByUserId( userId ) );
        bombvoidEntity.setScore( score );
        bombvoidEntity.setGameType( gameType );
        bombvoidRepository.save( bombvoidEntity );
    }
  }
}
