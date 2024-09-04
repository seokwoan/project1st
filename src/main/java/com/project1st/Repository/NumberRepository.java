package com.project1st.Repository;

import com.project1st.Entity.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository< NumberEntity , Long > {
}
