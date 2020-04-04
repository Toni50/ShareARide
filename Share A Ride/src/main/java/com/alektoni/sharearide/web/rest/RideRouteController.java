package com.alektoni.sharearide.web.rest;


import com.alektoni.sharearide.exceptions.NotFoundException;
import com.alektoni.sharearide.model.*;
import com.alektoni.sharearide.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//done
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/rideRoute")
public class RideRouteController {



    @Autowired
    private RideRouteService rideRouteService;

    @Autowired
    private RouteLocationService routeLocationService;


    public RideRouteController(){ }


//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/getAllRoutes", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<RideRoute> getAllRoutes() {
        return rideRouteService.getAll();
    }

    @RequestMapping(value = "/addRoute", method = RequestMethod.POST, produces = "application/json")
    public void addRoute(@RequestBody @Valid RideRoute rideRoute) {
        rideRouteService.save(rideRoute);
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST, produces = "application/json")
    public void addLocation(@RequestBody @Valid RouteLocation location) {
        Optional<RideRoute> optRoute = rideRouteService.findById(location.getRouteId());
        if(optRoute.isPresent()) {
            List<RouteLocation> locations = optRoute.get().getLocations();
            locations.add(location);
            optRoute.get().setLocations(locations);
            rideRouteService.save(optRoute.get());
        }else {
            throw new NotFoundException("RideRoute with id "+location.getRouteId()+" is not found");
        }

    }

    @RequestMapping(value = "/getLocations", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<RouteLocation> getLocations() {
        return routeLocationService.getAll();
    }

//TODO-----TEST--------------------------------------------------------------------------------------------------------------------





}
