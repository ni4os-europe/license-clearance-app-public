package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.License;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LicenseRepository extends MongoRepository<License,String> {
    @Override
    Optional<License> findById(String s);

    Optional<License> findByName(String name);

    @Query(value = "{'compatibleLicenses' : {$all : [?0]}}", fields = "{'name':1,'_id':0}")
    List<License> findCompatibleLicenses(List<String> givenLicenses);
}
