package com.example.iotdb.entity.iotdb;

import lombok.Data;

/**
 * @Author: Aiden
 */
@Data
public class MeasurementValue {

    private String path;

    private String timestamp;

    private String value;
}
