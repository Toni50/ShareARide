package com.alektoni.sharearide.web.rest;

import com.alektoni.sharearide.exceptions.NotFoundException;
import com.alektoni.sharearide.model.*;
import com.alektoni.sharearide.model.vm.MyPage;
import com.alektoni.sharearide.security.MyUserDetails;
import com.alektoni.sharearide.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/notification")
public class NotificationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyNotificationService myNotificationService;

    public NotificationController(){ }


//TODO-----PROD--------------------------------------------------------------------------------------------------------------------



    //Account -> Notifications
    @RequestMapping(value="/fetchNotifications", method= RequestMethod.GET)
    @ResponseBody
    public MyPage<MyNotification> findByNotificationToUserId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if(optUser.isPresent()){
            return myNotificationService.findByToUserId(optUser.get().getUserId(),page,pageSize);
        }else {
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }
//TODO-----PROD--------------------------------------------------------------------------------------------------------------------






//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/getAllNotifications", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<MyNotification> getAllNotifications() {
        return myNotificationService.getAll();
    }

    @RequestMapping(value="/getNotificationById", method= RequestMethod.GET)
    @ResponseBody
    public MyNotification getNotificationById(@RequestParam(name="id", required=true) Long id) {
        Optional<MyNotification> optMyNotification = myNotificationService.findById(id);
        if(optMyNotification.isPresent()) {
            return optMyNotification.get();
        }else{
            throw new NotFoundException("MyNotification with id "+id+" is not found");
        }
    }

    @RequestMapping(value = "/deleteNotification", method = RequestMethod.DELETE)
    public void deleteNotification(@RequestParam(name="id", required=true) Long id) {
        Optional<MyNotification> optMyNotification = myNotificationService.findById(id);
        if(optMyNotification.isPresent()) {
            myNotificationService.deleteById(id);
        }else {
            throw new NotFoundException("MyNotification with id "+id+" is not found");
        }
    }

//TODO-----TEST--------------------------------------------------------------------------------------------------------------------


}
