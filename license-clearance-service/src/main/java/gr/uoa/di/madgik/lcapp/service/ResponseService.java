package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import gr.uoa.di.madgik.lcapp.repository.ResponseRepository;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private AuthService authService;

    public Response saveResponse(Map<String, Object> payload){

        Response response = new Response();
        UserPrincipal userPrincipal = authService.getPrincipal();

        if(userPrincipal != null){
            response.setUserId(userPrincipal.getId());
        }
        response.setCreatedAt(new Date());
        response.setUpdatedAt(new Date());
        response.setAnswers(payload);
        return responseRepository.save(response);
    }

    public Response getResponseById(String id){
        return responseRepository.findById(id).orElse(null);
    }

    public void deleteById(String id){
        responseRepository.deleteById(id);
    }
}
