package com.project1st.Repository;

import com.project1st.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository< MemberEntity , Long > {
    MemberEntity findByUserId(String userId);

    MemberEntity findByEmail(String email);
}
