package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.form.Term;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VocabularyRepository extends MongoRepository<Vocabulary, String> {

    @Aggregation(pipeline = {
            "{ '$unwind': '$terms' }",
            "{ '$match': {'terms.id': ?0} }",
            "{ '$project': {'_id': 0} }",
            "{ '$project': {'id': '$terms.id', 'name': '$terms.name'} }"
        }
    )
    Optional<Term> termNameFromTermId(String termId);
}
