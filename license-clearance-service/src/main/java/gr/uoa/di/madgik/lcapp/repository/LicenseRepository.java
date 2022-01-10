package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.License;
import gr.uoa.di.madgik.lcapp.model.LicenseInfo;
import gr.uoa.di.madgik.lcapp.model.LicenseSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LicenseRepository extends MongoRepository<License,String>, CustomLicenseRepository {
    @Override
    Optional<License> findById(String s);

    Optional<License> findByName(String name);

    @Query(value = "{'compatibleLicenses' : {$all : [?0]}}", fields = "{'_id':1, 'name':1, 'type': 1}")
    List<License> findCompatibleLicenses(List<String> givenLicenses);

    @Query(value = "{}", fields = "{'_id':1, 'name':1, 'type':1}")
    List<License> findAllWithoutCompatibilityMatrix();

    @Query(value = "{'_id': ?0, 'compatibleLicenses' : {$all : [?1]}}")
    Optional<License> findCompatible(String id, List<String> licenses);

    @Query(value = "{'_id': ?0}", fields ="{'compatibleLicenses': 0}")
    LicenseInfo findLicenseInfo(String id);

    @Query(value = "{}", fields ="{'_id': 1, 'name': 1, 'type': 1}")
    List<LicenseSummary> findLicenseSummaries();

}
