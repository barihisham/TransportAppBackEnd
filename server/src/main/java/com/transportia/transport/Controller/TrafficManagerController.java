package com.transportia.transport.Controller;

import com.transportia.transport.Model.TrafficManager;
import com.transportia.transport.Service.TrafficManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trafficmanager")
public class TrafficManagerController {

    private final TrafficManagerService trafficManagerService;

    @Autowired
    public TrafficManagerController(TrafficManagerService trafficManagerService) {
        this.trafficManagerService = trafficManagerService;
    }

    @GetMapping
    public List<TrafficManager> getAllTrafficManagers(){
        return trafficManagerService.getAllTrafficManagers();
    }

    @PostMapping
    public TrafficManager addTrafficManager(@RequestBody TrafficManager trafficManager){
        return trafficManagerService.addTrafficManager(trafficManager);
    }

    @DeleteMapping(path = "{trafficManagerUsername}")
    public TrafficManager deleteTrafficManager(@PathVariable("trafficManagerUsername") String trafficManagerUsername){
        return trafficManagerService.deleteTrafficManager(trafficManagerUsername);
    }
}
