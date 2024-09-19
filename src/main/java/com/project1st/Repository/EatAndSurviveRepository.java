package com.project1st.Repository;

import com.project1st.Entity.EatAndSurviveEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EatAndSurviveRepository extends JpaRepository<EatAndSurviveEntity, Long > {

  List<EatAndSurviveEntity> findByMemberEntity_IdOrderByDateDesc(Long memberId, Pageable pageable);
}
