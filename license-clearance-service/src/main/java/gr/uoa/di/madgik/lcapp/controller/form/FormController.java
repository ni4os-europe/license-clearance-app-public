package gr.uoa.di.madgik.lcapp.controller.form;

import gr.uoa.di.madgik.lcapp.model.*;
import gr.uoa.di.madgik.lcapp.model.form.ClearanceSchema;
import gr.uoa.di.madgik.lcapp.model.form.Question;
import gr.uoa.di.madgik.lcapp.model.form.Section;
import gr.uoa.di.madgik.lcapp.model.form.Vocabulary;
import gr.uoa.di.madgik.lcapp.service.AuthService;
import gr.uoa.di.madgik.lcapp.service.ClearanceSchemaService;
import gr.uoa.di.madgik.lcapp.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FormController {

    @Autowired
    ClearanceSchemaService clearanceSchemaService;

    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    AuthService authService;

    @GetMapping("/schema")
    public ResponseEntity<ClearanceSchema> getSchemas(@RequestParam String workflow, @RequestParam(required = false) String version){

        if (version!=null){
            Optional<ClearanceSchema> schema = clearanceSchemaService.getSchema(workflow,version);
            return schema.map(ResponseEntity::ok).orElse(ResponseEntity.ok(null));
        }
        return ResponseEntity.ok(clearanceSchemaService.getSchema(workflow).get());
    }

//    @GetMapping("/sections")
//    public ResponseEntity<List<Section>> getSections(){
//        return ResponseEntity.ok(clearanceSchemaService.getSections());
//    }
//
//    @GetMapping("/questions")
//    public ResponseEntity<List<Question>> getQuestions(){
//        return ResponseEntity.ok(clearanceSchemaService.getQuestions());
//    }

    @GetMapping("/vocabularies")
    public ResponseEntity<List<Vocabulary>> getVocabularies(){
        return ResponseEntity.ok(vocabularyService.getAll());
    }

    @GetMapping("/vocabulary")
    public ResponseEntity<Vocabulary> getVocabulary(@RequestParam(name = "vocabulary_id") String id){

        Optional<Vocabulary> vocabulary = vocabularyService.getById(id);

        if (vocabulary.isPresent()){
            return new ResponseEntity<>(vocabulary.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
