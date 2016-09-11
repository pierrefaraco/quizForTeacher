package cnam.glg204.quiz.server.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {



    @Autowired
    UserDao userDao;
   
    /**
    *  methode appelé paar Spring à l'authentification (ioc)
    */
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {	
        User user =  userDao.findByMail(login);
        if (user == null) {     
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } 
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();      
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAccountType().name());
        grantedAuthorities.add(grantedAuthority);       
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
