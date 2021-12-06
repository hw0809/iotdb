package com.example.iotdb;

import com.example.iotdb.entity.iotdb.CreateTimeseriesParam;
import com.example.iotdb.entity.iotdb.InsertParam;
import com.example.iotdb.entity.iotdb.MeasurementValue;
import com.example.iotdb.mapper.iotdb.IOTDBMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class IotDBApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private IOTDBMapper iotdbMapper;

    @Test
    void testGetMeasurementPointLastValue() {
        System.out.println(iotdbMapper.getMeasurementPointLastValue("status", "root.sgcc.wf01.wt01"));
    }

    @Test
    void testSetStorageGroup() {
        iotdbMapper.setStorageGroup("root.sgcc");
    }

    @Test
    void testCreateTimeseries() {
        CreateTimeseriesParam param = new CreateTimeseriesParam();
        param.setPath("root.sgcc.wf01.wt01.status");
        param.setEncoding("PLAIN");
        param.setDataType("BOOLEAN");
        iotdbMapper.createTimeseries(param);
    }

    @Test
    void testInsert() {
        // root.ln.wf01.wt01(timestamp,status) values(1509465600000,true)
        InsertParam insertParam = new InsertParam();

        insertParam.setPath("root.sgcc.wf01.wt01");
        List<Object> field = new ArrayList<>();
        field.add("timestamp");
        field.add("status");
        //field.add("temperature");
        insertParam.setField(field);

        List<Object> fieldValue = new ArrayList<>();
        fieldValue.add(new Date().getTime());
        fieldValue.add(true);
        //fieldValue.add(null);
        insertParam.setFieldValue(fieldValue);

        System.out.println(iotdbMapper.insert(insertParam));
    }

    @Test
    void testSelectField() {
        List<MeasurementValue> temperature = iotdbMapper.selectField("status", "root.sgcc.wf01.wt01");
        System.out.println(temperature);
    }

    @Test
    void testDelete() {
        iotdbMapper.delete("root.sgcc.wf01.wt01.status", new Date().getTime());
    }

}
