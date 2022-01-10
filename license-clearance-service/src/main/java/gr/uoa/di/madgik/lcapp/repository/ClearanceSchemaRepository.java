package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClearanceSchemaRepository extends MongoRepository<ClearanceSchema,String> {

    Optional<ClearanceSchema> findByWorkflowAndVersionGreaterThan(String workflow, String version);

    Optional<ClearanceSchema> findByWorkflow(String workflow);
}
