package com.project1st.Service;

import com.project1st.DTO.NumberDto;
import com.project1st.Entity.NumberEntity;
import com.project1st.Repository.NumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {
  
  private final NumberRepository numberRepository; // Number repository 객체 생성

  public void scoreSave(NumberDto numberDto) {
    NumberEntity numberEntity = numberDto.createEntity();
    numberRepository.save( numberEntity );
  }
}
