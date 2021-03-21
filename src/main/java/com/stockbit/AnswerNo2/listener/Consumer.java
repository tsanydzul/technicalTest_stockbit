package com.stockbit.AnswerNo2.listener;

import com.stockbit.AnswerNo2.model.MappingData;
import com.stockbit.AnswerNo2.model.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Consumer {

    private LocalDateTime previousMinutes = null;

    private List<ConsumerRecord<String, Data>> listRecord = new ArrayList<>();

    private List<String> result = new ArrayList<>();

    @KafkaListener(topics = "tester6", groupId = "test-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Data> record) {
        LocalDateTime localDateTime = LocalDateTime.parse(record.value().getLocalDateTime());
        if(previousMinutes != null){
            long duration = Duration.between(previousMinutes.truncatedTo(ChronoUnit.MINUTES),localDateTime.truncatedTo(ChronoUnit.MINUTES)).toMinutes();
            if(duration > 0){
                calculateMap(listRecord, previousMinutes);
                listRecord.clear();
                listRecord.add(record);
                previousMinutes = localDateTime;
            }else{
                listRecord.add(record);
            }
        }else{
            previousMinutes = localDateTime;
            listRecord.add(record);
        }
    }

    public void calculateMap(List<ConsumerRecord<String, Data>> batchRecord, LocalDateTime time){
        HashMap<String, MappingData> dataContainer = new HashMap<>();
        String stringTime = time.getHour() +":"+ time.getMinute() +":00";
        for (ConsumerRecord<String, Data> data: batchRecord) {

            String key = data.key();
            long amount = data.value().getAmount();

            if(dataContainer.containsKey(key)){

                dataContainer.get(key).setMin(amount);
                dataContainer.get(key).setMax(amount);

            }else{

                MappingData mappingData = new MappingData();
                mappingData.setId(key);
                mappingData.setMax(amount);
                mappingData.setMin(amount);
                mappingData.setTime(stringTime);

                dataContainer.put(key, mappingData);

            }
        }

        for (String key :dataContainer.keySet()) {
            System.out.println(dataContainer.get(key).formatter());
        }
    }

}
