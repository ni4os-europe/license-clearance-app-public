package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.auth.Role;
import gr.uoa.di.madgik.lcapp.model.auth.User;
import gr.uoa.di.madgik.lcapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    RoleService roleService;


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
        Role role = roleService.findByName("ROLE_USER").orElseThrow(
                RuntimeException::new
        );
        user.setRoles(new HashSet<Role>(){{add(role);}});
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
