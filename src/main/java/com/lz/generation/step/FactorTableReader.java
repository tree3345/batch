package com.lz.generation.step;

import org.springframework.batch.item.support.ListItemReader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class FactorTableReader  extends ListItemReader {

    public FactorTableReader(List list) {
        super(list);
    }

    public String read() {
        String readData = (String) super.read();
        System.out.println("Reading data " + readData);
        return readData;
    }

}