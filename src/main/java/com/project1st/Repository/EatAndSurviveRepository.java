package com.project1st.Repository;

import com.project1st.Entity.EatAndSurviveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EatAndSurviveRepository extends JpaRepository<EatAndSurviveEntity, Long > {
  // 이름수정
}
