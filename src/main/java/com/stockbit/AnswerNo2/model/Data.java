package com.stockbit.AnswerNo2.model;

import org.springframework.stereotype.Repository;

@Repository
public class Data {
    private String id;
    private String localDateTime;
    private int amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Data() {
    }

    public Data(String id, String localDateTime, int amount) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", localDateTime:" + localDateTime +
                ", amount:" + amount +
                '}';
    }
}
