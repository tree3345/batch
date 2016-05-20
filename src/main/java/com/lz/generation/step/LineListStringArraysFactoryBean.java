package com.lz.generation.step;

import com.lz.batch.utils.MyDateUtil;
import com.lz.mapper.FactorTableMapper;
import com.lz.model.FactorTable;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class LineListStringArraysFactoryBean implements FactoryBean<List<String>> {


    @Override
    public List<String> getObject() throws Exception {
//        System.out.println(startDate+" "+endDate);
        List<String> list = new ArrayList<>();
        FactorTable table = new FactorTable();
        /*List<JenTableModel> jenTableModels = factorTableMapper.selectJenDataList(table);*/
      /*  String startDate = new SimpleDateFormat("yyyyMMdd").format(table.getDataBeginDate());
        String endDate = new SimpleDateFormat("yyyyMMdd").format(table.getDataEndDate()); */
        String startDate = "20150101";
        String endDate = "20160101";
//        int i = 1;
        while (Integer.parseInt(startDate) <= Integer.parseInt(endDate)) {
           /* String[] strs = new String[5];
            strs[0] = startDate*/;
            startDate = MyDateUtil.getSpecifiedDayAfter(startDate,"yyyyMMdd");
            list.add(startDate);
        }
        return list;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }



}
