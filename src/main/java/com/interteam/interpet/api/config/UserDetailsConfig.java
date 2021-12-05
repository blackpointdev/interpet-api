package com.interteam.interpet.api.config;


import com.interteam.interpet.api.controller.user.UserDto;
import com.interteam.interpet.api.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //todo
        UserDto user = UserRepository.find(email); //todo
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.withUsername(email)
                //.password(user.getPassword(email)) todo @OneToOne like in offer
                .authorities(user.getRoleName()).build();
    }
}
