package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.auth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
