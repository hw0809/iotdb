package com.example.iotdb.entity.iotdb;

import lombok.Data;

import java.util.List;

/**
 * @Author: Aiden
 */
@Data
public class InsertParam {

    private String path;

    private List<Object> field;

    private List<Object> fieldValue;
}
