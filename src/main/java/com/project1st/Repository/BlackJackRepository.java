package com.project1st.Repository;

import com.project1st.Entity.BlackJackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackJackRepository extends JpaRepository< BlackJackEntity , Long > {
}
