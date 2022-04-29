package com.duogglong.tm.repository;

import com.duogglong.tm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select e from User e where e.username = ?1 and e.active = true ")
    User findByUsername(String username);

    @Query("delete from User e where e.username = ?1 and e.active = true ")
    void deleteByUsername(String username);
}
