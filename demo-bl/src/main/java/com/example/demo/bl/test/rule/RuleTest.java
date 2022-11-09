package com.example.demo.bl.test.rule;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ruleTest
 * @Author yu.zhang
 * @Description
 * @Date 2022/7/25 17:47
 **/
public class RuleTest {


    public static void main(String[] args) {

        RuleDTO ruleDTO= new RuleDTO();
        PtpRuleDTO ptpRuleDTO =  new PtpRuleDTO();
        ptpRuleDTO.setRuleId("12312");
        ptpRuleDTO.setRuleName("测试名称-ptpRuleDTO");
        ptpRuleDTO.setPtpUtilization(new BigDecimal(20));
            List<String> ptpRateList = new ArrayList<>();
            ptpRateList.add("ALL");
            ptpRateList.add("2M");
            ptpRateList.add("FE");
            ptpRateList.add("GE");
        ptpRuleDTO.setPtpRateList(ptpRateList);
        ptpRuleDTO.setIsHiddenDanger("是");
            List<String> transmitModeList = new ArrayList<>();
            transmitModeList.add("ALL");
            transmitModeList.add("PTN");
            transmitModeList.add("SPN");
            transmitModeList.add("OTN");
            transmitModeList.add("SDH");
        ptpRuleDTO.setTransmitModeList(transmitModeList);

        ptpRuleDTO.setStatisticsPeriod("天");
            List<String> vendorList = new ArrayList<>();
            vendorList.add("ALL");
            vendorList.add("华为");
            vendorList.add("中兴");
            vendorList.add("烽火");
        ptpRuleDTO.setVendorList(vendorList);


        BandwidthRuleDTO bandwidthRuleDTO = new BandwidthRuleDTO();
        bandwidthRuleDTO.setRuleId("12312");
        bandwidthRuleDTO.setRuleName("测试名称-bandwidthRuleDTO");
        bandwidthRuleDTO.setBandwidthUtilization(new BigDecimal(20));
        bandwidthRuleDTO.setIsHiddenDanger("是");
        bandwidthRuleDTO.setTransmitModeList(transmitModeList);
        bandwidthRuleDTO.setStatisticsPeriod("天");
        bandwidthRuleDTO.setVendorList(vendorList);

        ruleDTO.setBandwidthRuleDTO(bandwidthRuleDTO);
        ruleDTO.setPtpRuleDTO(ptpRuleDTO);

        String jsonString = JSON.toJSONString(ruleDTO);

        System.out.println(jsonString);
    }
}
