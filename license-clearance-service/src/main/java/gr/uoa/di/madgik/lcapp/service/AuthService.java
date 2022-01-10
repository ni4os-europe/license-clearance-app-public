package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    public UserPrincipal getPrincipal(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal principal = ((UserPrincipal) authentication.getPrincipal());
            return principal;
        }
        else
        {
            System.out.println("Unauthenticated user\n\n");
            return null;
        }
    }
}
