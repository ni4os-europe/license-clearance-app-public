package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Section;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import gr.uoa.di.madgik.lcapp.repository.ClearanceSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClearanceSchemaService {

    @Autowired
    ClearanceSchemaRepository clearanceSchemaRepository;

    public void saveSchema(ClearanceSchema schema){
        clearanceSchemaRepository.insert(schema);
    }

    public ClearanceSchema getSchema(){
        return clearanceSchemaRepository.findAll().get(0);
    }

    public List<Section> getSections(){
        return clearanceSchemaRepository.findSections().get(0).getSections();
    }

    public List<Question> getQuestions(){
        return clearanceSchemaRepository.findQuestions().get(0).getQuestions();
    }

    public List<Vocabulary> getVocabularies() {
        return clearanceSchemaRepository.findVocabularies().get(0).getVocabularies();
    }

    public void deleteAll(){
        clearanceSchemaRepository.deleteAll();
    }
}
