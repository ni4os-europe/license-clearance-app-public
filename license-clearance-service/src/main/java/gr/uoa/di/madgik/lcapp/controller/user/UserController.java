package gr.uoa.di.madgik.lcapp.controller.user;

import com.lowagie.text.DocumentException;
import gr.uoa.di.madgik.lcapp.model.auth.User;
import gr.uoa.di.madgik.lcapp.model.clearance.Clearance;
import gr.uoa.di.madgik.lcapp.security.CurrentUser;
import gr.uoa.di.madgik.lcapp.security.UserPrincipal;
import gr.uoa.di.madgik.lcapp.service.ClearanceService;
import gr.uoa.di.madgik.lcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ClearanceService clearanceService;

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getUser(@CurrentUser UserPrincipal userPrincipal) throws Exception {
        return ResponseEntity.ok(userService.findById(userPrincipal.getId()));
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll() throws Exception {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/admin-create-pdf")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createPdf(@RequestParam Long id) throws Exception {
        Clearance cl = clearanceService.getClearanceById(id);
        Long userId = cl.getUserId();
        String reportPath = clearanceService.savePdfReport(cl.getId(), cl.getName(), userId == null ? "unauthenticated" : userService.findById(userId).getUsername());
        cl.setReportPath(reportPath);
        clearanceService.saveClearance(cl);
        return ResponseEntity.ok(reportPath);
    }

    @GetMapping("/user/clearances")
    public ResponseEntity<List<Clearance>> getClearances(@RequestParam(name = "id", required = false) Long user_id){
        return ResponseEntity.ok(clearanceService.getClearances(user_id));
    }

    @PostMapping("/user/update")
    public void updateUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @DeleteMapping(value = "/user/clearances/delete")
    public void deleteClearance(@RequestParam("id") Long id) {
        clearanceService.deleteClearance(id);
    }


}
