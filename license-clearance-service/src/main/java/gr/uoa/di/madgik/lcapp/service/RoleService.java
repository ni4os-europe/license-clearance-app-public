package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.auth.Role;
import gr.uoa.di.madgik.lcapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(String name){
    Role searchedRole = roleRepository.findByName(name).orElse(null);
        if (searchedRole != null) return searchedRole;
        return roleRepository.save(new Role(name));
    }

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }
}
