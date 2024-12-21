package com.rutujp78.messDekho_backend.Service;

import com.rutujp78.messDekho_backend.Model.User;
import com.rutujp78.messDekho_backend.Model.UserPrincipal;
import com.rutujp78.messDekho_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            System.out.println("User not found.");
            throw new UsernameNotFoundException("User not found");
        }
//        System.out.println(user.getPassword());
        return new UserPrincipal(user);
    }
}
