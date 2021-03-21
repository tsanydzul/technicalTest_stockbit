package com.stockbit.AnswerNo2.model;

import org.springframework.stereotype.Repository;

@Repository
public class MappingData {
    private String id;
    private long max = 0;
    private long min = 0;
    private String time;

    public MappingData() {
    }

    public MappingData(String id, long max, long min, String time) {
        this.id = id;
        this.max = max;
        this.min = min;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        if(this.max < max) {
            this.max = max;
        }
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        if(this.min>min || this.min == 0){
            this.min = min;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String formatter(){
        return this.time + "|" + this.id + "|high;" + this.max + "|low;" + this.min;
    }
}
