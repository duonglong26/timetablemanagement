//package com.duogglong.tm.core.auditing;
//
//import javax.persistence.PostRemove;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//
//import com.duogglong.tm.utils.CommonUtils;
//import com.duogglong.tm.utils.SecurityUtils;
//import java.time.LocalDateTime;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class EntityAuditListener {
//    public EntityAuditListener() {
//    }
//
//    @PostRemove
//    public void postRemove(AuditableEntity auditableEntity) {
//        LocalDateTime now = LocalDateTime.now();
//        auditableEntity.setModifyDate(now);
//        Object principal = SecurityUtils.getPrincipal();
//        if (CommonUtils.isNotNull(principal)) {
//            if (principal instanceof UserDetails) {
//                UserDetails user = (UserDetails)principal;
//                System.out.println("After Remove " + auditableEntity.toString() + " by" + user.getUsername());
//            } else {
//                System.out.println("After Remove " + auditableEntity.toString() + " by" + principal);
//            }
//        }
//
//    }
//
//    @PrePersist
//    public void beforePersist(AuditableEntity auditableEntity) {
//        LocalDateTime now = LocalDateTime.now();
//        auditableEntity.setCreateDate(now);
//        auditableEntity.setModifyDate(now);
//        Object principal = SecurityUtils.getPrincipal();
//        if (CommonUtils.isNotNull(principal)) {
//            if (principal instanceof UserDetails) {
//                UserDetails user = (UserDetails)principal;
//                auditableEntity.setCreatedBy(user.getUsername());
//                auditableEntity.setModifiedBy(user.getUsername());
//            } else {
//                auditableEntity.setCreatedBy(principal.toString());
//                auditableEntity.setModifiedBy(principal.toString());
//            }
//        } else {
//            auditableEntity.setCreatedBy("admin");
//        }
//
//    }
//
//    @PreUpdate
//    public void beforeMerge(AuditableEntity auditableEntity) {
//        LocalDateTime now = LocalDateTime.now();
//        auditableEntity.setModifyDate(now);
//        Object principal = SecurityUtils.getPrincipal();
//        if (CommonUtils.isNotNull(principal)) {
//            if (principal instanceof UserDetails) {
//                UserDetails user = (UserDetails)principal;
//                auditableEntity.setCreatedBy(user.getUsername());
//                auditableEntity.setModifiedBy(user.getUsername());
//            } else {
//                auditableEntity.setCreatedBy(principal.toString());
//                auditableEntity.setModifiedBy(principal.toString());
//            }
//        }
//
//    }
//}