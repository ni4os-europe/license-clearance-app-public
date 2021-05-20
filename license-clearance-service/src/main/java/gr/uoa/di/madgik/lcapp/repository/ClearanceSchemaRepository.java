package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClearanceSchemaRepository extends MongoRepository<ClearanceSchema,String> {

    @Query(value = "{}", fields = "{'sections':1,'_id':0}")
    List<ClearanceSchema> findSections();

    @Query(value = "{}", fields = "{'questions':1,'_id':0}")
    List<ClearanceSchema> findQuestions();

    @Query(value = "{}", fields = "{'vocabularies':1,'_id':0}")
    List<ClearanceSchema> findVocabularies();

}
