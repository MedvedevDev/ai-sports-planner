package dev.aisportsplanner.api;

import  jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/routes")
public class RouteGenerationController {
    
    @PostMapping("/generate")
    public RouteGenerationRequest generate(@Valid @RequestBody RouteGenerationRequest request) {
        return  request;
    }  
}
