package com.project1st.Repository;

import com.project1st.Entity.RememberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RememberRepository extends JpaRepository<RememberEntity, Long > {
}
