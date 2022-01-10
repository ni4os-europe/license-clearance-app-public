package gr.uoa.di.madgik.lcapp.repository;

import gr.uoa.di.madgik.lcapp.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;

public class CustomLicenseRepositoryImpl implements CustomLicenseRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<License> findLicensesWithCompatibleAttributes(List<String> permissions, List<String> noPermissions, List<String> prohibitions, List<String> noProhibitions, List<String> obligations, List<String> noObligations) {
        Query query = new Query();

        List<Criteria> criteria = new ArrayList<>();

        if(permissions != null && !permissions.isEmpty()) {
            criteria.add(Criteria.where("permissions").all(permissions));
        }

        if(noPermissions != null && !noPermissions.isEmpty()) {
            criteria.add(Criteria.where("permissions").nin(noPermissions));
        }

        if(prohibitions != null && !prohibitions.isEmpty()) {
            criteria.add(Criteria.where("prohibitions").all(prohibitions));
        }

        if(noProhibitions != null && !noProhibitions.isEmpty()) {
            criteria.add(Criteria.where("prohibitions").nin(noProhibitions));
        }

        if(obligations != null && !obligations.isEmpty()) {
            criteria.add(Criteria.where("obligations").all(obligations));
        }

        if(noObligations != null && !noObligations.isEmpty()) {
            criteria.add(Criteria.where("obligations").nin(noObligations));
        }


        if(!criteria.isEmpty()) {
            Criteria[] cArray = new Criteria[criteria.size()];

            cArray = criteria.toArray(cArray);

            query.addCriteria(new Criteria().andOperator(
                    cArray
            ));
        }

        return mongoTemplate.find(query, License.class);
    }
}
