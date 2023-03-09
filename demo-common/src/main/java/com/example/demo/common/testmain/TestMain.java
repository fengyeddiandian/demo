package com.example.demo.common.testmain;

//import com.google.common.primitives.Ints;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

import com.example.demo.common.xmlutil.GetFilesNames;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TestMain
 * @Author yu.zhang
 * @Description
 * @Date 2022/3/1 9:30
 **/
public class TestMain {

    public static void main(String[] args) {
//        List<String> fileList = new ArrayList<>();
//        String path = "E:\\工作资料\\需求\\电信\\OTMS接入\\getR es\\getResStandardData\\84ff3c95ba89959b";
//        GetFilesNames.getFileName(path, fileList);
//
//        fileList.stream().map(a -> a.split("\\.")[1]).collect(Collectors.toSet()).forEach(System.out::println);
//
//        List<Map<String, String>> maps = new ArrayList<>();
//        {
//            Map<String, String> map = new LinkedCaseInsensitiveMap<>();
//            map.put("RESOURCEID", "123");
//            maps.add(map);
//        }
//        {
//            Map<String, String> map = new LinkedCaseInsensitiveMap<>();
//            map.put("resourceid", "456");
//            maps.add(map);
//        }
//        {
//            Map<String, String> map = new LinkedCaseInsensitiveMap<>();
//            map.put("resourceId", "4156");
//            maps.add(map);
//        }
////        maps=objMapListKeyToUpperCase(maps);
////        Set<String> collect = maps.stream().map(dto -> dto.get("RESOURCEID") + "").collect(Collectors.toSet());
//        Set<String> collect = maps.stream().map(dto -> Objects.toString(dto.get("RESOURCEID"), "")).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
//        System.out.println(collect);

//        List<MeBean> list = new ArrayList<>();
//        list.add(new MeBean("123","123","123"));
//        list.add(new MeBean("123","1234","1234"));
//        list.add(new MeBean("456","456","456"));
//        list.add(new MeBean("456","4567","4567"));
//        list.add(new MeBean("789","789","789"));
//        list.add(new MeBean("789","7890","7890"));
//
//
//        Map<String, List<MeBean>> collect = list.stream().collect(Collectors.groupingBy(MeBean::getRingObjectId));
//        Map<String, List<Map<String, String>>> resourceId = maps.stream().collect(Collectors.groupingBy(a -> a.get("resourceId")));
//
//        resourceId.keySet().forEach(a->{
//            List<Map<String, String>> maps1 = resourceId.get(a);
//            maps1.forEach(bean->{
//                bean.put("123","123");
//            });
//        });
//
//        collect.keySet().forEach(a->{
//            List<MeBean> list1 = collect.get(a);
//            list1.forEach(bean->{
//                bean.setMeIndex(0);
//            });
//        });
//
//        list.forEach(System.out::println);
//        maps.forEach(System.out::println);
//        CheckResultDTO checkResultDTO = new CheckResultDTO();
//        checkResultDTO.setTableId("123");
//        checkResultDTO.setFiledName("A_MAC_PORT_USERLABEL");
//        checkResultDTO.setName("ce123");
//        String sql = " update t_snc_aggregate   set    " + checkResultDTO.getFiledName() + " = '"+checkResultDTO.getName()+"' where id = '"+checkResultDTO.getTableId()+"' " ;
//        System.out.println(sql);
//        String objectRef ="EMS=Huawei-NCE-T;MD=Huawei-NCE-T;MLSN=1;SNC=2021-06-24 13:20:00 - 162152-sdh";
//        String emsId ="";
//        String mlsn ="";
//        String snc ="";
//        List<String> collect = Arrays.stream(objectRef.split(";")).collect(Collectors.toList());
//        for (String str : collect) {
//            if (str.contains("MLSN=")){
//                mlsn=str.replace("MLSN=","");
//            }
//            if (str.contains("MD=")){
//                emsId=str.replace("MD=","");
//            }
//            if (str.contains("SNC=")){
//                snc=str.replace("SNC=","");
//            }
//        }
//
//        System.out.println(emsId);
//        System.out.println(mlsn);
//        System.out.println(snc);

//

//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//            String s = "123";
//            if (StringUtils.isNotBlank(s)){
//                break;
//            }
//        }
        Map<String, Object> params = new HashMap<>();
        List<Object> para = new ArrayList<Object>();
        String rowSize =null;
        String index =null;
        if(params.get("pageRowSize")!=null&&!"".equals(params.get("pageRowSize").toString())){
            rowSize =params.get("pageRowSize").toString();
        }
        if(params.get("pageIndex")!=null&&!"".equals(params.get("pageIndex").toString())){
            index =params.get("pageIndex").toString();
        }
        StringBuffer sqlBuf = new StringBuffer();
        if (rowSize!=null && index!=null) {
            sqlBuf.append(" select al.* from ( " );
        }
        sqlBuf.append("  select rownum rowno,a.TRANSMITMODE,a.MANAGEMENTDOMAIN,a.emsname,a.RINGNAME,a.MENAME ,b.MENAME  MENAMEs from  ");
        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"PTN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append("  (select 'PTN' TRANSMITMODE  ,p.MANAGEMENTDOMAIN,e.emsname,p.RINGNAME, MENAME  ");
        }
        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"SPN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append("  (select 'SPN' TRANSMITMODE  ,p.MANAGEMENTDOMAIN,e.emsname,p.RINGNAME, MENAME  ");
        }
        sqlBuf.append("   from ptn_ring p ");
        sqlBuf.append("   left join ptn_ringme  pr on p.OBJECTID=pr.ringOBJECTID  ");
        sqlBuf.append("   left join ems e on p.EMSOBJECTID=e.objectid where 1=1 ");
        if(params.get("city") != null && !((String)params.get("city")).equals("")) {
            sqlBuf.append(" and p.MANAGEMENTDOMAIN in ("+params.get("city")+") ");
        }
        if(params.get("ems") != null && !((String)params.get("ems")).equals("")) {
            sqlBuf.append(" and e.emsname in ("+params.get("ems")+") ");
        }
        sqlBuf.append("  and p.ISDUALHOMING = 0  and p.RINGRANK = '汇聚层' and pr.SERVICELEVEL='本地骨干'  ");

        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"PTN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append(" and ISSPN=0 ");
        }
        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"SPN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append(" and ISSPN=1 ");
        }
        sqlBuf.append("  group by p.MANAGEMENTDOMAIN,e.emsname ,p.RINGNAME,pr.MENAME ) a ");
        sqlBuf.append("  full join  ");
        sqlBuf.append("  (select p.RINGNAME, listagg (pr.MENAME, ',') WITHIN GROUP (ORDER BY pr.MENAME) MENAME   ");
        sqlBuf.append("  from ptn_ring p  ");
        sqlBuf.append("  left join ptn_ringme  pr on p.OBJECTID=pr.ringOBJECTID   ");
        sqlBuf.append("  left join ems e on p.EMSOBJECTID=e.objectid where 1=1 ");

        if(params.get("city") != null && !((String)params.get("city")).equals("")) {
            sqlBuf.append(" and p.MANAGEMENTDOMAIN in ("+params.get("city")+") ");
        }
        if(params.get("ems") != null && !((String)params.get("ems")).equals("")) {
            sqlBuf.append(" and e.emsname in ("+params.get("ems")+") ");
        }
        sqlBuf.append("  and p.ISDUALHOMING = 0  and p.RINGRANK = '汇聚层'    ");

        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"PTN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append(" and ISSPN=0 ");
        }
        if(params.get("transmitmode") != null && !((String)params.get("transmitmode")).equals("")&&"SPN".equals((String)params.get("transmitmode"))) {
            sqlBuf.append(" and ISSPN=1 ");
        }
        sqlBuf.append("  group by p.RINGNAME) b on a.RINGNAME=b.RINGNAME ");

        if (rowSize!=null && index!=null) {
            int pageRowSize = (Integer)params.get("pageRowSize");
            int pageIndex = (Integer)params.get("pageIndex");
            sqlBuf.append(" where ROWNUM <= ").append(pageIndex * pageRowSize).append(") al WHERE rowno >= ").append((pageIndex -1) * pageRowSize + 1 );
        }

        System.out.println(sqlBuf.toString());


    }

    public static List<Map<String, Object>> objMapListKeyToUpperCase(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> mapListTemp = new ArrayList<Map<String, Object>>();
        if (null != mapList && !mapList.isEmpty()) {
            Map<String, Object> maptemp = null;
            for (Map<String, Object> map : mapList) {
                maptemp = new HashMap<String, Object>();
                if (null != map) {
                    for (String key : map.keySet()) {
                        maptemp.put(key.toUpperCase(), map.get(key));
                    }
                }
                mapListTemp.add(maptemp);
            }
        }
        return mapListTemp;
    }

}
