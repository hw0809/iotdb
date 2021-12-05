package com.example.iotdb.mapper.mysql;

import com.example.iotdb.entity.mysql.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Aiden
 */
@Mapper
public interface UserMapper {

    List<User> getAllUsers();
}
