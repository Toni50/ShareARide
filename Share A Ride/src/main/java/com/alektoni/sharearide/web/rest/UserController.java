package com.alektoni.sharearide.web.rest;


import com.alektoni.sharearide.exceptions.MyException;
import com.alektoni.sharearide.exceptions.NotFoundException;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.Contact;
import com.alektoni.sharearide.model.User;
import com.alektoni.sharearide.security.MyUserDetails;
import com.alektoni.sharearide.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/user")
public class UserController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;


    public UserController(){ }

//TODO-----PROD--------------------------------------------------------------------------------------------------------------------

    //Register new user
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public void register(@RequestBody @Valid User user) {
        Optional<User> optUser = userService.findByEmail(user.getEmail());
        if(optUser.isPresent()) {
            throw new MyException("User with mail "+user.getEmail()+" already exists");
        }else{
            user.setActive(true);
            user.setRoles("ROLE_USER");
            userService.save(user);
        }
    }

    //user/2 in react
    @RequestMapping(value="/getUserById", method= RequestMethod.GET)
    @ResponseBody
    public User getUserById(@RequestParam(name="id", required=true) Long id) {
        Optional<User> optUser = userService.findById(id);
        if(optUser.isPresent()) {
            //todo hide password
            return optUser.get();
        }else{
            throw new NotFoundException("User with id "+id+" is not found");
        }
    }

    //user/2
    @RequestMapping(value="/getAccountInfo", method= RequestMethod.GET)
    @ResponseBody
    public User getAccountInfo() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if(optUser.isPresent()) {
            return optUser.get();
        }else{
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }




    // //Account -> About  (user can make changes to his profile details)
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());
        if(optUser.isPresent()) {
            return userService.save(user);
        }else{
            throw new NotFoundException("User with id "+user.getUserId()+" is not found");
        }
    }




    //Add Contact to user
    @RequestMapping(value = "/addContact", method = RequestMethod.GET, produces = "application/json")
    public void addContact(@RequestParam(name = "userId") Long userId, @RequestParam(name = "contactId") Long contactId) {
        //todo secure with auth
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isPresent()) {
            Optional<User> optContactUser = userService.findById(contactId);
            if (optContactUser.isPresent()) {
                Optional<Contact> optContact = contactService.findById(userId);
                if (optContact.isPresent()) {
                    //adding new contact to user
                    List<User> contacts = optContact.get().getContacts();
                    boolean contained = false;
                    for (int i = 0; i < contacts.size(); i++) {
                        if (contacts.get(i).getUserId() == optContactUser.get().getUserId()) {
                            contained = true;
                        }
                    }
                    if(contained==false){
                        contacts.add(optContactUser.get());
                        optContact.get().setContacts(contacts);
                        contactService.save(optContact.get());
                    }else{
                        throw new NotFoundException("Contact with id " + contactId + " is already added"
                        +" to User with id "+userId);
                    }
                    //adding new contact to user
                } else {
                    List<User> contacts = new ArrayList<>();
                    contacts.add(optContactUser.get());
                    Contact tmp = new Contact(optUser.get().getUserId(), contacts);
                    contactService.save(tmp);
                }
            } else {
                throw new NotFoundException("User(Contact) with id " + contactId + " is not found");
            }
        } else {
            throw new NotFoundException("User with id " + userId + " is not found");
        }
    }

    //get Contacts For User
    @RequestMapping(value = "/getContacts", method = RequestMethod.GET, produces = "application/json")
    public List<Contact> getContactsForUser(@RequestParam(name="userId") Long userId) {
        //todo secure with auth
        Optional<User> optUser = userService.findById(userId);
        if(optUser.isPresent()) {
            Optional<Contact> optContact = contactService.findById(userId);
            if(optContact.isPresent()) {
                return contactService.findByUserId(userId);
            }else{
                throw new NotFoundException("Contact with id "+userId+" is not found");
            }
        }else{
            throw new NotFoundException("User with id "+userId+" is not found");
        }
    }

//TODO-----PROD--------------------------------------------------------------------------------------------------------------------













//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<User> getAllUsers() {
        return userService.getAll();
    }


    //when deleting a user you should delete all cars for that user
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam(name="id") Long id) {
        Optional<User> optUser = userService.findById(id);
        if(optUser.isPresent()) {
            ArrayList<Car> cars =  carService.findByDriverId(id);
            for(int i=0;i<cars.size();i++){
                carService.deleteById(cars.get(i).getCarId());
            }
            userService.deleteById(id);
            return;
        }
        throw new NotFoundException("User with id "+id+" is not found");
    }

//TODO-----TEST--------------------------------------------------------------------------------------------------------------------




}
