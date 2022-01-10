package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.LicenseAttributeQuestions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseAttributeRepository extends MongoRepository<LicenseAttributeQuestions, String> {
}
