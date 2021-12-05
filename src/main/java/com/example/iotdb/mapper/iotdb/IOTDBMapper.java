package com.example.iotdb.mapper.iotdb;

import com.example.iotdb.entity.iotdb.CreateTimeseriesParam;
import com.example.iotdb.entity.iotdb.InsertParam;
import com.example.iotdb.entity.iotdb.MeasurementValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Aiden
 */
@Mapper
public interface IOTDBMapper {
    List<String> getAllStorageGroup();

    MeasurementValue getMeasurementPointLastValue(@Param("field")String field, @Param("path") String path);

    int setStorageGroup(@Param("group") String group);

    int insert(@Param("p") InsertParam param);

    List<MeasurementValue> selectField(@Param("filed") String filed, @Param("path") String path);

    int createTimeseries(@Param("p") CreateTimeseriesParam param);

    int delete(@Param("path") String path, @Param("t") Long time);
}
