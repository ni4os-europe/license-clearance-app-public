package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.model.ContactCategory;
import gr.uoa.di.madgik.lcapp.repository.ContactCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactCategoryService {


    @Autowired
    ContactCategoryRepository contactCategoryRepository;


    public void saveContactCategory(ContactCategory cat){
        contactCategoryRepository.save(cat);
    }

    public List<ContactCategory> getContactCategories(){

        List<ContactCategory> ret = new ArrayList<>();

        for (ContactCategory cat : contactCategoryRepository.findAll()) {
            ret.add(cat);
        }
        return ret;
    }
    public void deleteAll(){
        contactCategoryRepository.deleteAll();
    }
}
