package com.transportia.transport.Controller;

import com.transportia.transport.Model.Driver;
import com.transportia.transport.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @PostMapping
    public Driver addDriver(@RequestBody Driver driver){
        return driverService.addDriver(driver);
    }

    @GetMapping(path = "/getAllAvailableDrivers")
    public List<Driver> getAllAvailableDrivers(){
        return driverService.getAllAvailableDrivers();
    }

    @DeleteMapping(path = "{driverUsername}")
    public Driver deleteDriver(@PathVariable("driverUsername") String driverUsername){
        return driverService.deleteDriver(driverUsername);
    }

    @PutMapping
    public void setAvailability(@RequestBody Driver driver){
        driverService.setDriverAvailability(driver);
    }
}
