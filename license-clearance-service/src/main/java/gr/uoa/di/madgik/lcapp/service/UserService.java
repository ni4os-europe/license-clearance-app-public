package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.auth.User;
import gr.uoa.di.madgik.lcapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryService countryService;


    public User createUser(OidcUserInfo oidcUserInfo) {

        User user = new User();

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
//        user.setUsername(oidcUserInfo.getPreferredUsername());
        user.setUsername(oidcUserInfo.getEmail());
        user.setEmail(oidcUserInfo.getEmail());
        user.setFirstName(oidcUserInfo.getGivenName());
        user.setLastName(oidcUserInfo.getFamilyName());
        user.setCountry(countryService.getCountryByName(oidcUserInfo.getAddress().getCountry()));
        user.setPosition(oidcUserInfo.getAddress().getLocality());
        user.setAddress(oidcUserInfo.getAddress().getStreetAddress());
        user.setInstitutionName(oidcUserInfo.getClaim("eduperson_entitlement"));
        user.setActive(true);

        return userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public User findById(Long id) throws Exception {

        User user = userRepository.findById(id).orElseThrow(
                Exception::new
        );

        return user;
    }
}
