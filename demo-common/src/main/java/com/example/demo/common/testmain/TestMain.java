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
        List<String> fileList =  new ArrayList<>();
        String path ="E:\\工作资料\\需求\\电信\\OTMS接入\\getR es\\getResStandardData\\84ff3c95ba89959b";
        GetFilesNames.getFileName(path,fileList);

        fileList.stream().map(a->a.split("\\.")[1]).collect(Collectors.toSet()).forEach(System.out::println);

        List<Map<String,Object>> maps  = new ArrayList<>();
        {Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
            map.put("RESOURCEID","123");
            maps.add(map);}
        {Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
            map.put("resourceid","456");
            maps.add(map);}
        {Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
            map.put("resourceId","4156");
            maps.add(map);}
//        maps=objMapListKeyToUpperCase(maps);
//        Set<String> collect = maps.stream().map(dto -> dto.get("RESOURCEID") + "").collect(Collectors.toSet());
        Set<String> collect = maps.stream().map(dto -> Objects.toString(dto.get("RESOURCEID"),"")).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        System.out.println(collect);





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
