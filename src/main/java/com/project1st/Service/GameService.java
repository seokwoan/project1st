package com.project1st.Service;

import com.project1st.Constant.GameType;
import com.project1st.Entity.EatAndSurviveEntity;
import com.project1st.Entity.NumberEntity;
import com.project1st.Repository.EatAndSurviveRepository;
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
  private final EatAndSurviveRepository eatAndSurviveRepository;  // EatAndSurvive repository 객체 생성

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
        eatAndSurviveEntity.setMemberEntity( memberRepository.findByUserId(userId) );
        eatAndSurviveEntity.setScore( score );
        eatAndSurviveEntity.setGameType( gameType );
        eatAndSurviveRepository.save( eatAndSurviveEntity );
    }
  }
}
