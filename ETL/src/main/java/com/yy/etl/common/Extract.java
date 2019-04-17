package com.yy.etl.common;

import lombok.Setter;

import java.util.concurrent.BlockingQueue;

@Setter
public abstract class Extract<T> extends ETLUtil {
    // 给 extract 增加个队列,数据给Transform调用
    protected BlockingQueue<T> outQueue;
}
