package com.example.iotdb.entity.iotdb;

import lombok.Data;

/**
 * @Author: Aiden
 */
@Data
public class CreateTimeseriesParam {

    private String path;

    private String dataType;

    private String encoding;
}
