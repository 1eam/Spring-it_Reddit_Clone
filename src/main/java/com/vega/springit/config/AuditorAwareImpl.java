package com.vega.springit.config;

import com.vega.springit.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //      Link class cant get logged in user (cause Link extends Auditable) to fix this (see code below) (this is only a development-only  issue.
        if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return Optional.of("master@gmail.com");
        }
        return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());

    }
}
