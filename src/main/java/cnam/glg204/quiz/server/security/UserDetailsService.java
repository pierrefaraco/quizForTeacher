package cnam.glg204.security;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.service.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    //private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
	UserDao userDao;

    // Inversion de controle, Méthode appelé 
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {	
    	System.out.println(" + "  + login);
        User user =  userDao.findUserByMail(login);
        if (user == null) {     
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } 
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();      
        System.out.println("AccountType " + user.getAccountType().name() );
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAccountType().name());
        grantedAuthorities.add(grantedAuthority);       
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
