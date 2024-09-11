package com.project1st.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EatAndSurvive extends JpaRepository<com.project1st.Entity.EatAndSurvive, Long > {
}
