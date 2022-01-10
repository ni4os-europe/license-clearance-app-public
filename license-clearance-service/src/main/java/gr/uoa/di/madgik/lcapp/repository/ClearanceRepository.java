package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClearanceRepository extends CrudRepository<Clearance,Long> {

    List<Clearance> findAllByUserId(Long userId);

    Optional<Clearance> findByUuid(String uuid);
}
