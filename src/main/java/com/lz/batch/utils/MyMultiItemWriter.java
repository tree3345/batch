package com.lz.batch.utils;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * 写处理类。
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class MyMultiItemWriter<T> implements ItemWriter<T> {

    /***/
    private List<ItemWriter<? super T>> delegates;

    public void setDelegates(List<ItemWriter<? super T>> delegates) {

        this.delegates = delegates;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void write(List<? extends T> items) throws Exception {

        for (ItemWriter itemWriter : delegates) {
            itemWriter.write(items);
        }
    }
}