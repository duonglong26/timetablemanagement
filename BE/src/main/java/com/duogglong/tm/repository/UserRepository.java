package com.duogglong.tm.repository;

import com.duogglong.tm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select e from User e where e.username = ?1")
    User findByUsername(String username);
}
