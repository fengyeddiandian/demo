package com.example.demo.common.TestController;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 联通集团EMS清单查询，为前端提供下拉框数据
 * @Author apple
 * @Date 2021/5/18 18:26
 * @Version 1.0
 */
@Mapper
public interface LtEmsListMapper {
    /**
     * 前端提供模糊查询省份接口
     * @return
     */
    List<String> selectProvince();

    /**
     * 根据省份查询所有地市，支持模糊查询
     * @param province
     * @param city
     * @return     province,city
     */
    List<String> selectCity(@Param("province") String province , @Param("city") String city);

    /**
     * 根据省份 地市查询厂家 支持厂家模糊查询
     * @param province
     * @param city
     * @param factory
     * @return
     */
    List<String> selectFactory(@Param("province") String province , @Param("city") String city , @Param("factory") String factory);

    /**
     * 查询emsType
     * @param emsType
     * @return
     */
    List<String> selectEmsType(@Param("emsType") String emsType);

    /**
     * 根据省份地市查询emsName
     * @param province
     * @param city
     * @param emsName
     * @return
     */
    List<String> selectEmsName(@Param("province") String province , @Param("city") String city , @Param("emsName") String emsName);

    /**
     * 查询采集省份记录表，根据采集完成的省份进行自动核查结果入库。
     * @return
     */
    List<Map<String,Object>> selectCollectProvince();

    /**
     * 删除采集省份记录表已完成核查入库的省份
     * @param province
     */
    int deleteCompleteProvince(@Param("province") String province);
}
