package com.yy.etl.common;

import com.yy.etl.entity.Person;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtractData extends Extract<Person> {
    private static Random r = new Random();

    @Override
    protected void process() {
        for (int i = 0; i < 10; i++) {
            try {
                // 随机休眠0-1000毫秒，表示获取数据
                Thread.sleep(r.nextInt(1000));
                outQueue.put(new Person(i, "zs" + i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}