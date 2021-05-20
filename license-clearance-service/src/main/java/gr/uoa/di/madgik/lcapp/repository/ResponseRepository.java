package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends MongoRepository<Response,String> {

}
