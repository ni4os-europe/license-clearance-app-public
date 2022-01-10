package gr.uoa.di.madgik.lcapp.service;


import gr.uoa.di.madgik.lcapp.model.clearance.ClearanceSubmission;
import gr.uoa.di.madgik.lcapp.model.clearance.Response;
import gr.uoa.di.madgik.lcapp.repository.ResponseRepository;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private AuthService authService;

    public Response saveResponse(ClearanceSubmission payload){

        Response response = new Response();
        UserPrincipal userPrincipal = authService.getPrincipal();

        if(userPrincipal != null){
            response.setUserId(userPrincipal.getId());
        }
        response.setCreatedAt(new Date());
        response.setUpdatedAt(new Date());
        response.setClearanceSubmission(payload);
        return responseRepository.save(response);
    }

    public Response getResponseById(String id){
        return responseRepository.findById(id).orElse(null);
    }

    public void deleteById(String id){
        responseRepository.deleteById(id);
    }
}
