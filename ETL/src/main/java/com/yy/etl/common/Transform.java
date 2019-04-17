package com.yy.etl.common;

import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
public abstract class Transform<inT, outT> extends ETLUtil {
    // 进队列，数据来源于 extract
    protected BlockingQueue<inT> inQueue;
    // 出队列，数据给 Load
    protected BlockingQueue<outT> outQueue;
    // 上一级的线程数, extract的线程数
    protected AtomicInteger parentThreadNum;

}
