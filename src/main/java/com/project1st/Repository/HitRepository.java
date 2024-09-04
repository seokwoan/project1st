package com.project1st.Repository;

import com.project1st.Entity.HitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HitRepository extends JpaRepository< HitEntity , Long > {
}
