package com.alektoni.sharearide.web.rest;


import com.alektoni.sharearide.exceptions.NotFoundException;

import com.alektoni.sharearide.model.Car;
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

//done
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;


    public CarController() {
    }


//TODO-----PROD--------------------------------------------------------------------------------------------------------------------

    //Account -> Car -> User adds car
    @RequestMapping(value = "/addCar", method = RequestMethod.POST, produces = "application/json")
    public Car addCar(@RequestBody @Valid Car car) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());
        if (optUser.isPresent()) {
            List<Car> cars = carService.findByDriverId(optUser.get().getUserId());
            if (cars.size() == 10 && car.getCarId() == -1) {
                throw new NotFoundException("User with id " + optUser.get().getUserId() + " has max(10) amount of cars");
            } else {
                car.setDriver(optUser.get());
                if (car.getActive()) {
                    for (Car tmp : cars) {
                        tmp.setActive(false);
                        carService.save(tmp);
                    }
                }
                return carService.save(car);
            }
        } else {
            throw new NotFoundException("User with mail " + myUserDetails.getUsername() + " is not found");
        }
    }


    //Account -> Cars
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Car> getCarsForUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if (optUser.isPresent()) {
            return carService.findByDriverId(optUser.get().getUserId());
        } else {
            throw new NotFoundException("User with mail " + myUserDetails.getUsername() + " is not found");
        }

    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.DELETE)
    public void deleteCar(@RequestParam(name = "id") Long id) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if (optUser.isPresent()) {
            Optional<Car> optCar = carService.findById(id);
            if (optCar.isPresent()) {
                if(optUser.get().getUserId().equals(optCar.get().getDriver().getUserId())){
                    carService.deleteById(id);
                }else{
                    throw new NotFoundException("User who sent this request is not driver of this car");
                }
            } else {
                throw new NotFoundException("Car with id " + id + " is not found");
            }
        } else {
            throw new NotFoundException("User with mail " + myUserDetails.getUsername() + " is not found");
        }
    }


//TODO-----PROD--------------------------------------------------------------------------------------------------------------------


//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/updateCar", method = RequestMethod.PUT)
    public void updateCar(@RequestBody Car car) {
        //can not change driver of existing car
        Optional<Car> c = carService.findById(car.getCarId());
        if (c.isPresent()) {
            User driver = c.get().getDriver();
            car.setDriver(driver);
            carService.save(car);
        } else {
            throw new NotFoundException("Car with id " + car.getCarId() + " is not found");
        }
    }





    @RequestMapping(value = "/getAllCars", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Car> getAllCars() {
        return carService.getAll();
    }

    @RequestMapping(value = "/getCarById", method = RequestMethod.GET)
    @ResponseBody
    public Car getCarById(@RequestParam(name = "id", required = true) Long id) {
        Optional<Car> optCar = carService.findById(id);
        if (optCar.isPresent()) {
            return optCar.get();
        } else {
            throw new NotFoundException("Car with id " + id + " is not found");
        }
    }
//TODO-----TEST--------------------------------------------------------------------------------------------------------------------


}
