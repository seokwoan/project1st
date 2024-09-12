package com.project1st.Repository;

import com.project1st.Entity.NumberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends JpaRepository< NumberEntity , Long > {


  List<NumberEntity> findByMemberEntity_IdOrderByDateDesc(Long memberId, Pageable pageable);
}
