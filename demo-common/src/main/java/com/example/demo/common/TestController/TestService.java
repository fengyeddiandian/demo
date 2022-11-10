package com.example.demo.common.TestController;

public interface TestService {

     void getString1(String key);

     default void getString2(String key){
          System.out.println("接口类方法2");
     };

}
