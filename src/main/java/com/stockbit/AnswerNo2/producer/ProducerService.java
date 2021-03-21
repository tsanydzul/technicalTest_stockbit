package com.stockbit.AnswerNo2.producer;

import com.stockbit.AnswerNo2.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, Data> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    public String TOPIC;

    public String sendData(BufferedReader reader){
        try{
            while (reader.ready()) {
                String line = reader.readLine();
                if(line.indexOf("|")>=0 && line.matches(".*\\d.*")){
                    int indexBarrier = line.indexOf("|");
                    String idAndData = line.substring(indexBarrier+1,line.length());
                    int indexSecBarrier = idAndData.indexOf("|");

                    String date = line.substring(0,indexBarrier);
                    String id = idAndData.substring(0, indexSecBarrier);
                    String stringAmount = idAndData.substring(indexSecBarrier+1);
                    int amount = Integer.parseInt(stringAmount);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

                    Data data = new Data(id,dateTime.toString(),amount);
                    kafkaTemplate.send(TOPIC, id, data);
                }
            }
            return "Success";
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
