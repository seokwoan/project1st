package com.project1st.Repository;

import com.project1st.Entity.OmokEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmokRepository extends JpaRepository< OmokEntity , Long > {
}
