package gr.uoa.di.madgik.lcapp.service;

import gr.uoa.di.madgik.lcapp.model.clearance.ClearanceSubmission;
import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Term;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import gr.uoa.di.madgik.lcapp.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VocabularyService {

    @Autowired
    VocabularyRepository vocabularyRepository;


    public List<Vocabulary> getAll(){
        return vocabularyRepository.findAll();
    }

    public Optional<Vocabulary> getById(String id){
        return vocabularyRepository.findById(id);
    }

    public String getTermNameFromTermId(String termId)  {
        Optional<Term> term = vocabularyRepository.termNameFromTermId(termId);

        return term.isPresent() ? term.get().getName() : null;
    }

    public void replaceTermIdsWithNames(ClearanceSubmission data, ClearanceSchema clearanceSchema) {

        for (Question q: clearanceSchema.getQuestions()){
            if (!q.getResponseType().equals("DropDown") && !q.getResponseType().equals("Accordion"))
                continue;
            String qId = q.getId();
            String sId = q.getSectionId();

            for (Map<String, Object> answerMap: data.getSections().get(sId)){
                answerMap.put(qId, getTermNameFromTermId((String)answerMap.get(qId)));
            }
        }

    }

}
