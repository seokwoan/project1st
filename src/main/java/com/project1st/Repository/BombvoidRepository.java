package com.project1st.Repository;

import com.project1st.Entity.BombvoidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombvoidRepository extends JpaRepository<BombvoidEntity, Long > {
}
