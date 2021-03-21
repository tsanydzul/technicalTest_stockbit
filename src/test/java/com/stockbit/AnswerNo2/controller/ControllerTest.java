package com.stockbit.AnswerNo2.controller;

import com.stockbit.AnswerNo2.producer.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = Controller.class)
@ComponentScan({"com.stockbit.AnswerNo2"})
public class ControllerTest {

    @Autowired
    private ProducerService kafkaProducer;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validFile()throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/kafka/publish/test3.txt")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
