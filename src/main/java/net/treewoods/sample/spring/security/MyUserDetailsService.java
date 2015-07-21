package net.treewoods.sample.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = null;
        if( username !=null && username.equals("admin")){
        user = new MyUser(username,"system","AAA");
        }
        if(user == null) {
            throw new UsernameNotFoundException("No user found for username '" + username +"'.");
        }
        return user;
    }
}
