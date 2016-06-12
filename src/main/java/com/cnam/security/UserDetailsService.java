package com.cnam.security;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.service.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
	UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
    	System.out.println(" + "  + login);
      //  log.debug("Authenticating {}", login);
        User user =  userDao.findUserByMail(login);
 
        if (user == null) {
      
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } 
	
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        AccountType authority = user.getAccountType();
        System.out.println("AccountType " + AccountType.getName(authority) + " " + authority );
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AccountType.getName(authority));
        grantedAuthorities.add(grantedAuthority);       

        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
