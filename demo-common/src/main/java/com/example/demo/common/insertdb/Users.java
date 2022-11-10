package com.example.demo.common.insertdb;

import lombok.Data;

/**
 * @ClassName Users
 * @Author yu.zhang
 * @Description
 * @Date 2022/2/22 9:17
 **/
@Data
public class Users extends userDB {
    Integer id;

    String name ;

    String sex;

}
