package gr.uoa.di.madgik.lcapp.repository;


import gr.uoa.di.madgik.lcapp.model.ContactCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactCategoryRepository extends CrudRepository<ContactCategory,Long> {
}
