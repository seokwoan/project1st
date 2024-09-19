package com.project1st.Repository;

import com.project1st.Entity.BombvoidEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BombvoidRepository extends JpaRepository<BombvoidEntity, Long > {
  List<BombvoidEntity> findByMemberEntity_IdOrderByDateDesc(Long memberId, Pageable pageable);
}
