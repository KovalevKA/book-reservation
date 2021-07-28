package com.example.bookreservation.config;

import com.example.bookreservation.service.security.UserAuthenticationService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserAuthenticationService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
        throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s,
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
        throws AuthenticationException {
        final Object token = usernamePasswordAuthenticationToken.getCredentials();
        return Optional.ofNullable(token)
            .map(String::valueOf)
            .flatMap(userService::findByToken)
            .orElseThrow(() -> new UsernameNotFoundException("Bad token " + token))
            ;
    }
}
