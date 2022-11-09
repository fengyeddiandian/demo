package com.example.demo.bl.test.rule;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ruleTest
 * @Author yu.zhang
 * @Description
 * @Date 2022/7/25 17:47
 **/
public class RuleTest1 {


    public static void main(String[] args) {

        ExternalRuleDTO ruleDTO= new ExternalRuleDTO();
        OpticSectionRuleDTO ptpRuleDTO =  new OpticSectionRuleDTO();
        ptpRuleDTO.setUtilization(new BigDecimal(20));
        List<String> ptpRateList = new ArrayList<>();
            ptpRateList.add("ALL");
            ptpRateList.add("国干");
            ptpRateList.add("省干");
            ptpRateList.add("核心");
        ptpRuleDTO.setOpticSectionList(ptpRateList);
        ptpRuleDTO.setStatisticsPeriod("天");
        ptpRuleDTO.setIsHiddenDanger("是");

        PipeHoleRuleDTO PipeHoleRuleDTO =  new PipeHoleRuleDTO();
        PipeHoleRuleDTO.setUtilization(new BigDecimal(20));
        PipeHoleRuleDTO.setOpticSectionList(ptpRateList);
        PipeHoleRuleDTO.setStatisticsPeriod("天");
        PipeHoleRuleDTO.setIsHiddenDanger("是");
        ruleDTO.setPipeHoleRuleDTO(PipeHoleRuleDTO);
        ruleDTO.setOpticSectionRuleDTO(ptpRuleDTO);
        String jsonString = JSON.toJSONString(ruleDTO);

        System.out.println(jsonString);

        String paramJson="{\"opticSectionRuleDTO\":{\"aa\":\"\",\"statisticsPeriod\":\"天\",\"opticSectionList\":[\"一干\",\"二干\"],\"bb\":\"\",\"utilization\":\"1\",\"cc\":\"\",\"isHiddenDanger\":\"是\",\"ruleId\":\"externalResourceUtilizationPlanning\"},\"pipeHoleRuleDTO\":{\"aa\":\"\",\"opticSectionList\":[\"一干\"],\"bb\":\"\",\"utilization\":\"2\",\"cc\":\"\",\"isHiddenDanger\":\"否\",\"ruleId\":\"externalResourceUtilizationPlanning\",\"statisticsPeriod\":\"\"}}";

        ExternalRuleDTO ruleDTO1 = JSON.parseObject(paramJson, ExternalRuleDTO.class);
        OpticSectionRuleDTO opticSectionRuleDTO = ruleDTO1.getOpticSectionRuleDTO();
        List<String> opticSectionList = opticSectionRuleDTO.getOpticSectionList();
        String other="";
        String opticSection="";
        if (opticSectionList !=null && opticSectionList.size()>0 ){
            if (opticSectionList.contains("其它")){
                opticSectionList.remove("其它");
                String opticSectionStr="一干,二干,城域核心,城域汇聚,城域接入";
                other= Arrays.stream(opticSectionStr.split(",")).filter(a->!opticSectionList.contains(a)).map(b->"'"+b+"'").collect(Collectors.joining(","));
            }else {
                opticSection = opticSectionList.stream().map(b -> "'" + b + "'").collect(Collectors.joining(","));
            }
        }

        System.out.println();
    }
}
