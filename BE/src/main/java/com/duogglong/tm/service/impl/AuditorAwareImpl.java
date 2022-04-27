package com.duogglong.tm.service.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//@Service
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception ex) {
            return Optional.of("duogglong");
        }
    }

}