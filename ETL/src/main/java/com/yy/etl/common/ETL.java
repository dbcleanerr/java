package com.yy.etl.common;

import com.rits.cloning.Cloner;

import javax.swing.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ETL {
    // 工作对象
    protected Extract extract;
    protected Transform transform;
    protected Load load;

    // 用到的队列
    protected BlockingQueue eOutQueue;
    protected BlockingQueue tOutQueue;

    // 线程数
    protected AtomicInteger eThreadNum, tThreadNum, lThreadNum;

    // 计数对象
    private ConcurrentMap<String, AtomicLong> counter = new ConcurrentHashMap<>();

    protected int totalThreadNum = 32;

    public ConcurrentMap<String, AtomicLong> getCounter() {
        return counter;
    }

    /**
     * 预处理
     * @throws Exception
     */
    protected void setup() throws Exception {

    }

    /**
     * 扫尾
     * @throws Exception
     */
    protected void cleanup() throws Exception {

    }

    public void run() throws Exception {
        // 线程数据控件
        if (eThreadNum.get() + tThreadNum.get() + lThreadNum.get() > totalThreadNum) {
            throw new Exception("Thread num of e+t+l greater than total thread num.");
        }

        setup();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(totalThreadNum);

        Cloner cloner = new Cloner();

        int eTNum = eThreadNum.get();
        for (int i = 0; i < eTNum; i++) {
            Extract e = cloner.deepClone(extract);
            e.setOutQueue(eOutQueue);
            e.setCounter(counter);
            e.setThreadNum(eThreadNum);
            fixedThreadPool.execute(e);
        }
        Thread.sleep(500);

        int tTNum = tThreadNum.get();
        for(int i=0; i<tTNum; i++) {
            Transform t = cloner.deepClone(transform);
            t.setInQueue(eOutQueue);
            t.setOutQueue(tOutQueue);
            t.setParentThreadNum(eThreadNum);
            t.setCounter(counter);
            t.setThreadNum(tThreadNum);
            fixedThreadPool.execute(t);
        }
        Thread.sleep(500);

        int lTNum = lThreadNum.get();
        for(int i=0; i<lTNum; i++) {
            Load l = cloner.deepClone(load);
            l.setInQueue(tOutQueue);
            l.setParentThreadNum(tThreadNum);
            l.setCounter(counter);
            l.setThreadNum(lThreadNum);
            fixedThreadPool.execute(l);
        }
        Thread.sleep(500);

        fixedThreadPool.shutdown();

        while (fixedThreadPool.isTerminated()) {
            Thread.sleep(1000);
        }

        cleanup();
    }
}
