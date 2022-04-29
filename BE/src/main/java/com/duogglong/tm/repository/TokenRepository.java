package com.duogglong.tm.repository;

import com.duogglong.tm.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("select e from Token e where e.username = ?1 and e.active = true ")
    Token findByUsername(String username);

    @Modifying
    @Transactional
    @Query("delete from Token where accessToken = ?1 and active = true ")
    void deleteByAccessToken(String accessToken);

    @Modifying
    @Transactional
    @Query("delete from Token where username = ?1 and active = true ")
    void deleteByUsername(String username);
}
