package com.example.demo.bl.test.rule;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RuleDTO
 * @Author yu.zhang
 * @Description
 * @Date 2022/7/14 14:52
 **/
@Data
public class PtpRuleDTO {
    /**
     * 规则名称
     */
    private String ruleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 统计周期
     */
    private String statisticsPeriod;
    /**
     * 厂家列表
     */
    private List<String> vendorList;
    /**
     * 设备类型列表
     */
    private List<String> transmitModeList;
    /**
     * 端口速率列表
     */
    private List<String> ptpRateList;
    /**
     * 端口利用率阈值
     */
    private BigDecimal ptpUtilization;
    /**
     * 是否隐患派单
     */
    private String isHiddenDanger;








}
