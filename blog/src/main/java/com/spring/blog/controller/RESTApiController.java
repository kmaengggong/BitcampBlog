package com.spring.blog.controller;

import com.spring.blog.dto.BmiDTO;
import com.spring.blog.dto.PersonDTO;
import org.apache.coyote.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/resttest")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class RESTApiController {
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String hello(){
        return "안녕하세요";
    }

    @RequestMapping(value="/foods", method=RequestMethod.GET)
    public List<String> foods(){
        List<String> foodList = List.of("탕수육", "똠양꿍", "돈가스");
        return foodList;
    }

    @RequestMapping(value="/person", method=RequestMethod.GET)
    public PersonDTO person(){
        PersonDTO personDTO = PersonDTO.builder()
                .id(1)
                .name("홍길동")
                .age(300)
                .build();
        return personDTO;
    }

    @RequestMapping(value="/person-list", method=RequestMethod.GET)
    public ResponseEntity<?> personList(){
        PersonDTO personDTO1 = PersonDTO.builder()
                .id(1)
                .name("홍길동")
                .age(300)
                .build();
        PersonDTO personDTO2 = PersonDTO.builder()
                .id(2)
                .name("김자바")
                .age(20)
                .build();
        PersonDTO personDTO3 = PersonDTO.builder()
                .id(3)
                .name("바이선")
                .age(30)
                .build();
        List<PersonDTO> personList = List.of(personDTO1, personDTO2, personDTO3);

        // .ok()는 상태 코드(200)를 반환하고, 뒤에 연달아 붙은 body()에 붙은 실제 리턴자료를 저장?
        return ResponseEntity.ok().body(personList);
    }

    @RequestMapping(value="/bmi", method=RequestMethod.GET)
    public ResponseEntity<?> bmi(BmiDTO bmiDTO){
        if(bmiDTO.getHeight() == 0) return ResponseEntity.badRequest().body("Height can not be zero");

        double result = bmiDTO.getWeight() / Math.pow((bmiDTO.getHeight()/100), 2);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Asdf", "qwer");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(result);
    }

    @RequestMapping(value="/bmi2", method=RequestMethod.GET)
    public ResponseEntity<?> bmi2(@RequestBody BmiDTO bmiDTO){
        if(bmiDTO.getHeight() == 0) return ResponseEntity.badRequest().body("Height can not be zero");

        double result = bmiDTO.getWeight() / Math.pow((bmiDTO.getHeight()/100), 2);

        return ResponseEntity
                .ok()
                .body(result);
    }
}
