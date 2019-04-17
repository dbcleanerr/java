package com.yy.etl.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Data
public abstract class ETLUtil implements Runnable {
    // 计数器
    private ConcurrentMap<String, AtomicLong> counter;
    // 线程数
    private AtomicInteger threadNum;

    /**
     * 增加记录到 counter
     * 如果存在 key，则更新value(old+new)
     * 如果不存在，写一条记录
     * @param key
     * @param num
     */
    protected void counterAdd(String key, Long num) {
        AtomicLong cnt = counter.putIfAbsent(key, new AtomicLong(num));

        if (cnt != null) {
            cnt.addAndGet(num);
        }
    }

    protected void counterIncrement(String key) {
        counterAdd(key, 1L);
    }

    protected abstract void process();

    @Override
    public void run() {
        log.debug(String.format("Begin to run [%s], thread num [%d].", this.getClass().getSimpleName(), threadNum.get()));
        process();
        // 把当前使用的线程减掉
        threadNum.decrementAndGet();
        log.debug(String.format("[%s] completed.", this.getClass().getSimpleName()));
    }
}
