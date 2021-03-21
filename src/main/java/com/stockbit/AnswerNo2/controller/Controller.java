package com.stockbit.AnswerNo2.controller;

import com.stockbit.AnswerNo2.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("kafka")
public class Controller {

    @Autowired
    private ProducerService kafkaProducer;

    @GetMapping("/publish/{fileName}")
    public String sendLine(@PathVariable String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileName)));
            return kafkaProducer.sendData(reader);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
