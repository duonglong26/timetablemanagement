package com.duogglong.tm.utils;

import java.util.Iterator;
import java.util.Set;

import com.duogglong.tm.entity.Role;
import com.duogglong.tm.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {
    public SecurityUtils() {
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return CommonUtils.isNotNull(authentication) && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

//    public static boolean isUserInRole(User user, String role) {
//        if (CommonUtils.isNull(user)) {
//            return false;
//        } else {
//            Set<GrantedAuthority> roles = (Set)user.getAuthorities();
//            Iterator var3 = roles.iterator();
//
//            GrantedAuthority ga;
//            do {
//                if (!var3.hasNext()) {
//                    return false;
//                }
//
//                ga = (GrantedAuthority)var3.next();
//            } while(!(ga instanceof Role) || !ga.getAuthority().equals(role));
//
//            return true;
//        }
//    }

    public static User getCurrentUser() {
        if (!isAuthenticated()) {
            return null;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User)authentication.getPrincipal();
        }
    }

    public static Object getPrincipal() {
        if (!isAuthenticated()) {
            return null;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getPrincipal();
        }
    }

//    public static void setCurrentUser(User user) {
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

    public static String getHashPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean passwordsMatch(String encryptedPassword, String plainPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
