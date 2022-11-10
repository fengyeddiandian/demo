package com.example.demo.common.TestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @ClassName TestServiceImpl
 * @Author yu.zhang
 * @Description
 * @Date 2022/3/10 10:00
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    TestMapper testMapper;
    @Override
    public void getString1(String key1) {
        List<Map<String, Object>> ptnPfLinKByEms = testMapper.selectCount("");
        List<PfLink> dataList = new ArrayList<>();
        ptnPfLinKByEms.forEach(map->{
            PfLink pfLink= new PfLink();
            pfLink.setObjectid(StringUtils.asString(map.get("OBJECTID")));
            pfLink.setLinkname(StringUtils.asString(map.get("LINKNAME")));
            pfLink.setCity(StringUtils.asString(map.get("CITY")));
            pfLink.setVendor(StringUtils.asString(map.get("VENDOR")));
            pfLink.setSourceNename(StringUtils.asString(map.get("SOURCE_NENAME")));
            pfLink.setDestNename(StringUtils.asString(map.get("DEST_NENAME")));
            pfLink.setEmsid(StringUtils.asString(map.get("EMSID")));
            pfLink.setTransmitmode(StringUtils.asString(map.get("TRANSMITMODE")));
            pfLink.setSourceNeid(StringUtils.asString(map.get("SOURCE_NEID")));
            pfLink.setDestNeid(StringUtils.asString(map.get("DEST_NEID")));
            pfLink.setSourcePortid(StringUtils.asString(map.get("SOURCE_PORTID")));
            pfLink.setDestPortid(StringUtils.asString(map.get("DEST_PORTID")));
            pfLink.setSourcePortname(StringUtils.asString(map.get("SOURCE_PORTNAME")));
            pfLink.setDestPortname(StringUtils.asString(map.get("DEST_PORTNAME")));
            pfLink.setIncludeRingname(StringUtils.asString(map.get("INCLUDE_RINGNAME")));
            pfLink.setSourceNeLevel(StringUtils.asString(map.get("SOURCE_NE_LEVEL")));
            pfLink.setDestNeLevel(StringUtils.asString(map.get("DEST_NE_LEVEL")));
            pfLink.setRingtype(StringUtils.asString(map.get("RINGTYPE")));
            pfLink.setDistrict(StringUtils.asString(map.get("DISTRICT")));
            dataList.add(pfLink);
        });
        log.info("---流量工具分析环网数据大小："+dataList.size());
        Map<String, List<PfLink>> includeRingnameMap = dataList.stream().collect(Collectors.groupingBy(PfLink::getIncludeRingname));
        log.info("---环网名称LIST：："+includeRingnameMap.keySet().size());
        includeRingnameMap.keySet().forEach(key-> {
            List<PfLink> pfLinks = includeRingnameMap.get(key);
            if (pfLinks != null && pfLinks.size() > 0) {
                log.info("---流量工具分析环网数据大小："+key +"----"+pfLinks.size());
            }
        });
    }


}
