package com.yy.etl.common;

import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
public abstract class Load<T> extends ETLUtil {
    protected BlockingQueue<T> inQueue;
    // 上一级的线程数, transform的线程数
    protected AtomicInteger parentThreadNum;
}
