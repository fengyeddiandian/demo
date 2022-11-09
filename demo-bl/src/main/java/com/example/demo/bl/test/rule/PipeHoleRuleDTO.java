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
public class PipeHoleRuleDTO {
    /**
     * 统计周期
     */
    private String statisticsPeriod;
    /**
     * 光缆级别
     */
    private List<String> opticSectionList;
    /**
     * 利用率阈值
     */
    private BigDecimal utilization;
    /**
     * 是否隐患派单
     */
    private String isHiddenDanger;
}
