package com.yy.etl.common;

import com.yy.etl.entity.Person;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TransformData extends Transform<Person, Person> {
    private static Random r = new Random();

    @Override
    protected void process() {
        while (true) {
            try {
                Thread.sleep(r.nextInt(1000));
                Person person = inQueue.take();
                person.setScore(person.getId() * 100);
                outQueue.put(person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
