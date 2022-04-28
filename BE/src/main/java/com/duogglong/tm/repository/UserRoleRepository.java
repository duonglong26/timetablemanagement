package com.duogglong.tm.repository;

import com.duogglong.tm.entity.Role;
import com.duogglong.tm.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select e.role from UserRole e where e.user.id = ?1")
    List<Role> findByUserId(long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from user_role as ur where ur.user = ?1", nativeQuery = true)
    void deleteByIdUser(long username);

}
