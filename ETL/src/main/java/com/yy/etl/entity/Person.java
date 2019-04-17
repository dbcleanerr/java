package com.yy.etl.entity;

import lombok.Data;

@Data
public class Person {
    private int id;
    private String name;
    private int score;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }
}
