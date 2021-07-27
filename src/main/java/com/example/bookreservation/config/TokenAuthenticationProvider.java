package com.example.bookreservation.config;

import com.example.bookreservation.service.security.UserService;
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
    private UserService userService;

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
            .flatMap(userService::getByToken)
            .orElseThrow(() -> new UsernameNotFoundException("Bad token " + token))
            ;
    }
}
