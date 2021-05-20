package gr.uoa.di.madgik.lcapp.controller.form;

import gr.uoa.di.madgik.lcapp.model.*;
import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Section;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import gr.uoa.di.madgik.lcapp.service.AuthService;
import gr.uoa.di.madgik.lcapp.service.ClearanceSchemaService;
import gr.uoa.di.madgik.lcapp.service.ContactCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class FormController {

    @Autowired
    ClearanceSchemaService clearanceSchemaService;

    @Autowired
    ContactCategoryService contactCategoryService;

    @Autowired
    AuthService authService;


    // Theoretically, we could have more than one schemas
    @GetMapping("/schema")
    public ResponseEntity<ClearanceSchema> getSchemas(){
        return ResponseEntity.ok(clearanceSchemaService.getSchema());
    }

    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getSections(){
        return ResponseEntity.ok(clearanceSchemaService.getSections());
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions(){
        return ResponseEntity.ok(clearanceSchemaService.getQuestions());
    }

    @GetMapping("/vocabularies")
    public ResponseEntity<List<Vocabulary>> getVocabularies(){
        return ResponseEntity.ok(clearanceSchemaService.getVocabularies());
    }

    @GetMapping("/contact-categories")
    public ResponseEntity<List<ContactCategory>> getContactCategories(){
        return ResponseEntity.ok(contactCategoryService.getContactCategories());
    }

}
