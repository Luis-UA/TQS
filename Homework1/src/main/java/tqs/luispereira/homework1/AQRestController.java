package tqs.luispereira.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AQRestController {

    @Autowired
    AQService service;

    @GetMapping("/info/{city}")
    public ResponseEntity<Object> getjsonData(@PathVariable("city") String city){
        if(!service.fillModel(city)){
            return new ResponseEntity<>("Invalid city", HttpStatus.NOT_FOUND);
        }else{
            String response = service.getModel().toString();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
