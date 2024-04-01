package ifinit.com.vendas.service.impl;

import ifinit.com.vendas.domain.entity.User;
import ifinit.com.vendas.domain.repositories.UserRepository;
import ifinit.com.vendas.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public User save(User user){
        return repository.save(user);
    }

    public UserDetails authentic( User user){
        UserDetails u = loadUserByUsername(user.getLogin());
        boolean equalsPassword =   passwordEncoder.matches(user.getPassword(), u.getPassword());
        if (equalsPassword){
            return u;
        }

        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  repository
                .findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found in system"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"} ;

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();

    }
}
