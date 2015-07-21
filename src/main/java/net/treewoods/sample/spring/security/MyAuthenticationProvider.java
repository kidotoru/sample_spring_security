package net.treewoods.sample.spring.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        String name = auth.getName();
        if ( auth.getCredentials() == null ) {
            throw new BadCredentialsException("認証エラー発生");
        }
        String password = auth.getCredentials().toString();
        UserDetails user = this.userDetailsService.loadUserByUsername(name);
        if (user != null && user.getPassword().equals(password)) {
            Authentication a = new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
            return a;
        } else {
           throw new BadCredentialsException("認証エラー発生");
        }
    }

    @Override
    public boolean supports(Class<?> token) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
    }
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
