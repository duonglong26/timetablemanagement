package com.duogglong.tm.repository;

import com.duogglong.tm.entity.Role;
import com.duogglong.tm.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select e.role from UserRole e where e.user.id = ?1")
    List<Role> findByUserId(long userId);

}
