package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.auth.User;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserPrincipalService implements UserDetailsService {


    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userService.findByEmail(username);
        if (user == null)
            return null;
        else
            return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws Exception {
        return UserPrincipal.create(userService.findById(id));
    }
}
