package jupgo.jupgoserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public ResponseEntity<HttpStatus> healthCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
