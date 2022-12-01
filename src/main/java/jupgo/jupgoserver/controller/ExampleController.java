package jupgo.jupgoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

    // http://localhost:9090/api/example
    @GetMapping(path = "/example")
    public String Example(){
        return "example";
    }

}
