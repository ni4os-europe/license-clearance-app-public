package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.model.LicenseInfo;
import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Section;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import gr.uoa.di.madgik.lcapp.repository.ClearanceSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClearanceSchemaService {

    @Autowired
    ClearanceSchemaRepository clearanceSchemaRepository;

    public void saveSchema(ClearanceSchema schema){
        clearanceSchemaRepository.insert(schema);
    }

    public Optional<ClearanceSchema> getSchema(String workflow, String version){
        return clearanceSchemaRepository.findByWorkflowAndVersionGreaterThan(workflow,version);
    }

    public Optional<ClearanceSchema> getSchema(String workflow){
        return clearanceSchemaRepository.findByWorkflow(workflow);
    }

    public void deleteAll(){
        clearanceSchemaRepository.deleteAll();
    }


}
