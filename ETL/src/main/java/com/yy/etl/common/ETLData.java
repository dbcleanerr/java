package com.yy.etl.common;

import com.yy.etl.entity.Person;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ETLData extends ETL {
    public ETLData() {
        eOutQueue = new ArrayBlockingQueue(20);
        tOutQueue = new ArrayBlockingQueue(100);

        eThreadNum = new AtomicInteger(1);
        tThreadNum = new AtomicInteger(1);
        lThreadNum = new AtomicInteger(1);

        extract = new ExtractData();
        transform = new TransformData();
        load = new LoadData();
    }

    public static void main(String[] args) {
        ETLData etlData = new ETLData();

        try {
            etlData.run();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
