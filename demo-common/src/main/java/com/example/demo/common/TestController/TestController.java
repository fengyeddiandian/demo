package com.example.demo.common.TestController;

import com.example.demo.common.olpotn.TransLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
//    @Autowired
    private TestService testServiceImpl;

    @Value("${ZDY.ip:56587}")
    private String Ip;

    @Value("${ZDY.ip:false}")
    private Boolean Ips;
//    @Resource
//    private OlptootnMapper olptootnMapper;

    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }


    @RequestMapping("/getString")
    public String getStringTest(String key){
        testServiceImpl.getString1(key);
        return "1";
    }



    private static  String getCollectFileTimeByHN(String collectFileName) throws ParseException {
        String collectFileTime;
        int startIndex = collectFileName.lastIndexOf("_",collectFileName.lastIndexOf("-") )+1  ; //黑龙江时间字符串接去
        int endIndex  = startIndex+13;
        if ( collectFileName.length() > endIndex ) {

            collectFileTime = collectFileName.substring( startIndex , endIndex );
            if (!isDigit(collectFileTime)) {
                collectFileTime=collectFileTime.replace("-","");
            }
            if ( isDigit(collectFileTime) ) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
                Date d = formatter.parse(collectFileTime);
                return String.valueOf(d.getTime());
            } else {
                return String.valueOf(Long.MAX_VALUE);
            }
        } else {
            return String.valueOf(Long.MAX_VALUE);
        }
    }
    private static  String getCollectFileTimeByHN(String file_name,String emsType) throws ParseException {
        if (file_name.indexOf("-PTN-") > 0 || file_name.indexOf("_PTN_") > 0){
            emsType="PTN";
        }else if (file_name.indexOf("-SPN-") > 0 || file_name.indexOf("_SPN_") > 0){
            emsType="SPN";
        }else if (file_name.indexOf("-OTN-") > 0 || file_name.indexOf("_OTN_") > 0){
            emsType="OTN";
        }
        return "";
    }
    public static void main(String[] args) {
        int a =1223;
//    Long aLong = Convert.toLong(a);
//    Short aShort = Convert.toShort(a);
//    log.info(String.valueOf(aShort));
//    log.info(String.valueOf(aLong));

//String st="/home/dccp_tnms_hn/data/4300HWCSE/T_Huawei_PTN_FTP_UN-HWNCE-2-P_CM_LBS_258260_1440_202106300000-P1.xml.gz";
//    String file_name ="adsa-PTN-asd";
//    String emsType ="asda-PTN-asdasd";

        String date ="2021-08-09 09:50:02.112";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date parse = simpleDateFormat.parse(date);

            simpleDateFormat = new SimpleDateFormat("HHmmssS");
            String format = simpleDateFormat.format(parse);
            System.out.println(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

//    @RequestMapping("/getController")
//    @ApiOperation("测试接口")
//    public Integer getController(String ems) {
//
//        log.info("获取全部需要分析数据");
////        List<Map<String, Object>> maps = olptootnMapper.selectCount(ems);
////        Map<String, List<Map<String, Object>>> collect =
////                maps.stream().filter(a -> a.get("NE_ID")!=null).collect(Collectors.groupingBy(a -> String.valueOf(a.get("PSUB_NET_ID"))));
////        //1个网元
////        List<String> collect2 = collect.keySet().stream().filter(a -> collect.get(a).size() == 1).collect(Collectors.toList());
////        //2个网元
////        List<String> collect3 = collect.keySet().stream().filter(a -> collect.get(a).size() == 2).collect(Collectors.toList());
////        //多于2个网元
////        List<String> collect4 = collect.keySet().stream().filter(a -> collect.get(a).size() > 2).collect(Collectors.toList());
////
////
////        Set<String> collect1 = collect3.stream().map(a ->
////                collect.get(a).stream().map(b -> b.get("NE_ID") + "").collect(Collectors.joining(","))
////
////
////        ).collect(Collectors.toSet());
////
////        log.info("网元对"+collect1.size());
//
//
//        List<TransLink> list= new ArrayList<>();
////        collect3.forEach(dto->{
////            List<Map<String, Object>> maps1 = collect.get(dto);
////            String ne_id = maps1.stream().map(b -> "'" + b.get("NE_ID") + "'").collect(Collectors.joining(","));
//        String  ne_id ="'UUID:c8391940-9699-11eb-984a-fa163eb7b22a', 'UUID:c83a9fe0-9699-11eb-984a-fa163eb7b22a'";
//
//            List<Map<String, Object>> selectLink = olptootnMapper.selectlink(ne_id);
//            List<Map<String, Object>> allRightList = filterDate(selectLink);
//            if (allRightList.size()>0){
//                createDTO(allRightList,list);
//            }
////        });
//        log.info("分析数据完成，数据量："+list.size());
//        if (list.size()>0){
//            log.info("开始插入数据！");
////            insertData(list);
//        }
//        log.info("完成");
//        return 1;
//    }
//
//    private void insertData(List<TransLink> dto1) {
//        dto1.forEach(dto->{
//            olptootnMapper.insertData(dto);
//        });
//    }
//
//    private void createDTO(List<Map<String, Object>> allRightList, List<TransLink> list) {
//
//
//        allRightList.forEach(map->{
//            String aUserLabel = String.valueOf(map.get("AUSERLABLE"));
//            String aOtnMeName="";
//            String aliasName="";
//            String[] split = aUserLabel.split("&");
//            if (split.length>2){
//                aOtnMeName=split[1];
//            }
//            if (split.length>4){
//                aliasName=split[3]+";"+split[4];
//            }else {
//                return;
//            }
//            String zUserLabel = String.valueOf(map.get("ZUSERLABLE"));
//            String zOtnMeName="";
//            String zliasName="";
//            String[] split1 = zUserLabel.split("&");
//            if (split1.length>2){
//                zOtnMeName=split1[1];
//            }
//            if (split1.length>4){
//                zliasName=split1[3]+";"+split1[4];
//            }else {
//                return;
//            }
//            if (StringUtils.isNotBlank(aOtnMeName)&&StringUtils.isNotBlank(zOtnMeName)){
//                List<Map<String, Object>> maps = olptootnMapper.selectOtnMe(aOtnMeName, zOtnMeName);
//
//                List<String> neIdList = maps.stream().map(dto -> dto.get("NEID") + "").collect(Collectors.toList());
//                List<Map<String, Object>> otnLinkList= new ArrayList<>();
//                if (neIdList.size()>1){
//                   otnLinkList = olptootnMapper.selectOtnLink(neIdList.get(0), neIdList.get(1));
//                }
//                if (otnLinkList.size()==2){
//                    pinJieDate(otnLinkList,map,aOtnMeName,zOtnMeName,aliasName,zliasName,list);
//                }
//            }
//        });
//    }

    private void pinJieDate(List<Map<String, Object>> otnLinkList, Map<String, Object> olpMap, String aOtnMeName, String zOtnMeName, String aliasName
            , String zliasName, List<TransLink> resultList) {

        Map<String, Object> otnMap =
                otnLinkList.stream().filter(a -> aOtnMeName.equals(a.get("ANENAME")) && zOtnMeName.equals(a.get("ZNENAME"))).findFirst().orElse(new HashMap<>());
        if (otnMap.keySet().size()>0){
            TransLink otnToOlp = new TransLink();
            otnToOlp.setLinkId("UUID:"+ UUID.randomUUID());
            otnToOlp.setUserlabel(String.valueOf(olpMap.get("AUSERLABLE")));
            otnToOlp.setAliasname(aliasName);
            //OTN a ->OLP a
            otnToOlp.setANeId(String.valueOf(otnMap.get("ANEID")));
            otnToOlp.setAPtpId(String.valueOf(otnMap.get("APTPID")));
            otnToOlp.setAEmsId(String.valueOf(otnMap.get("EMSID")));

            otnToOlp.setZNeId(String.valueOf(olpMap.get("ANE")));
            otnToOlp.setZPtpId(String.valueOf(olpMap.get("APTPID")));
            otnToOlp.setZEmsId(String.valueOf(olpMap.get("EMSID")));

            otnToOlp.setDirection("1");
            otnToOlp.setLayerRate("47");
            otnToOlp.setLinkType("1");
            otnToOlp.setDataSource("3");
            otnToOlp.setInsertTime(new Date());
            resultList.add(otnToOlp);

            TransLink olpToOtn = new TransLink();
            olpToOtn.setLinkId("UUID:"+ UUID.randomUUID());
            olpToOtn.setUserlabel(String.valueOf(olpMap.get("ZUSERLABLE")));
            olpToOtn.setAliasname(zliasName);
            //OLP Z ->OTN z
            olpToOtn.setANeId(String.valueOf(olpMap.get("ZNE")));
            olpToOtn.setAPtpId(String.valueOf(olpMap.get("ZPTPID")));
            olpToOtn.setAEmsId(String.valueOf(olpMap.get("EMSID")));

            olpToOtn.setZNeId(String.valueOf(otnMap.get("ZNEID")));
            olpToOtn.setZPtpId(String.valueOf(otnMap.get("ZPTPID")));
            olpToOtn.setZEmsId(String.valueOf(otnMap.get("EMSID")));

            olpToOtn.setDirection("1");
            olpToOtn.setLayerRate("47");
            olpToOtn.setLinkType("1");
            olpToOtn.setDataSource("3");
            olpToOtn.setInsertTime(new Date());
            resultList.add(olpToOtn);
        }
    }


    List<Map<String, Object>> filterDate(List<Map<String, Object>> list){
        //过滤符合规范的数据
        List<Map<String, Object>> rightList = list.stream().filter(a -> {
            String aboardModel = String.valueOf(a.get("ABOARDMODEL"));
            String zboardModel = String.valueOf(a.get("ZBOARDMODEL"));
            if ("EDFA".equals(aboardModel)) {
                if ("EDFA".equals(zboardModel)){
                    return false;
                }else if (a.get("ZUSERLABLE")==null){
                    return false;
                }else if (!String.valueOf(a.get("ZUSERLABLE")).contains("&")){
                    return false;
                }
            }else if (a.get("AUSERLABLE")==null){
                return false;
            }else {
                if (!String.valueOf(a.get("AUSERLABLE")).contains("&")){
                    return false;
                }else if (!"EDFA".equals(zboardModel)){
                    if (a.get("ZUSERLABLE")==null||!String.valueOf(a.get("ZUSERLABLE")).contains("&")){
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());
        //过滤完全正确数据
        List<Map<String, Object>> allRightList = rightList.stream().filter(a -> {
            String aboardModel = String.valueOf(a.get("ABOARDMODEL"));
            String zboardModel = String.valueOf(a.get("ZBOARDMODEL"));
            return !"EDFA".equals(aboardModel) && !"EDFA".equals(zboardModel);
        }).collect(Collectors.toList());
        //过滤需要拼接数据
        List<Map<String, Object>> halfRightList = rightList.stream().filter(a -> {
            String aboardModel = String.valueOf(a.get("ABOARDMODEL"));
            String zboardModel = String.valueOf(a.get("ZBOARDMODEL"));
            return "EDFA".equals(aboardModel) || "EDFA".equals(zboardModel);
        }).collect(Collectors.toList());
        //过滤拼接数据右部分
        List<Map<String, Object>> rHalfRightList = halfRightList.stream().filter(a -> {
            String aboardModel = String.valueOf(a.get("ABOARDMODEL"));
            return "EDFA".equals(aboardModel) ;
        }).collect(Collectors.toList());
        //过滤拼接数据左部分
        List<Map<String, Object>> lHalfRightList = halfRightList.stream().filter(a -> {
            String zboardModel = String.valueOf(a.get("ZBOARDMODEL"));
            return "EDFA".equals(zboardModel) ;
        }).collect(Collectors.toList());
        //拼接数据，并将数据添加到完全正确数据list
        lHalfRightList.forEach(a->{
            Object zBOARDID = a.get("ZBOARDID");
            if (zBOARDID!=null){
                List<Map<String, Object>> aboardid = rHalfRightList.stream().filter(b -> zBOARDID.equals(b.get("ABOARDID"))).collect(Collectors.toList());
                if (aboardid.size()>0){
                    heBin(a,aboardid.get(0));
                    allRightList.add(a);
                }
            }
        });
        return allRightList;
    }



    void heBin(Map<String, Object> oldData , Map<String, Object> newData ){
        oldData.put("ZNE",newData.get("ZNE"));
        oldData.put("ZBOARDMODEL",newData.get("ZBOARDMODEL"));
        oldData.put("ZBOARDID",newData.get("ZBOARDID"));
        oldData.put("ZUSERLABLE",newData.get("ZUSERLABLE"));
        oldData.put("ZPTPID",newData.get("ZPTPID"));
    }

}
