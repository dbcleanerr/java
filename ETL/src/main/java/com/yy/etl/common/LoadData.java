package com.yy.etl.common;

import com.yy.etl.entity.Person;

import java.util.Random;

public class LoadData extends Load<Person> {
    private static Random r = new Random();

    @Override
    protected void process() {
        while (true) {
            try {
                Thread.sleep(r.nextInt(1000));
                Person person = inQueue.take();
                person.setName(person.getName() + ":ok");
                System.out.println(person.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
