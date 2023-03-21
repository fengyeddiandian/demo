package com.example.demo.common.xmlutil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.io.SAXReader;
@Slf4j
public class Analyser {
    public String emsId;
    public List<String> keyList = null;

    // 保存对象的rmuid
    private HashMap<String, String> rmuidKeyMap = new HashMap<String, String>();
    // 保存重复的rmuid
    public HashMap<String, HashMap<String, String>> repeatRmuidKeyMap =
            new HashMap<String, HashMap<String, String>>();

    private List<String> needToRemoveMessyCodes =
            new ArrayList<String>() {
                private static final long serialVersionUID = 1L;

                {
                    add("CardAnalyser");
                    add("PtpAnalyser");
                    add("PortAnalyser");
                    add("TopoLinkAnalyser");
                }
            };

    /**
     * @desc: SAXReader方式读取XML数据文件
     * @author <chengsheng.wang@zznode.com>
     * @since 2017-9-8
     * @param fileNameList void
     */
    public int doSAXReader(List<String> fileNameList, String emsID, boolean b, String type) {
        FieldNameElementHandler fieldNameElementHandler = new FieldNameElementHandler();
        FieldValueElementHandler fieldValueElementHandler = new FieldValueElementHandler();
//        FieldElementHandler fieldElementHandler = new FieldElementHandler();
        SAXReader reader = new SAXReader();
        reader.addHandler("/DataFile/Objects/FieldName", fieldNameElementHandler);
        reader.addHandler("/DataFile/Objects/FieldValue", fieldValueElementHandler);
        //reader.addHandler("/DataFile/FileHeader", fieldElementHandler);
        Map<String, Integer> map = new HashMap<>();
        Set<String> setKey = new HashSet<>();
        int total = 0;
        for (String fileName : fileNameList) {
            preSAXReaderFile(reader, fileName);
            List<List<String>> objectList2 = fieldValueElementHandler.getObjectList();
            List<String> keyList = fieldNameElementHandler.getKeyList();
            int index = 0;
            int size = objectList2.size();
            if (objectList2.size() > 0) {
                if (b) {
                    if ("rack".equals(type)) {
                        List<List<String>> collect =
                                objectList2.stream()
                                        .filter(list -> "rack".equals(list.get(5)) || "sub_rack".equals(list.get(5)))
                                        .collect(Collectors.toList());
                        List<List<String>> collect1 = objectList2.stream().filter(a -> !setKey.add(a.get(0))).collect(Collectors.toList());
                        collect1.forEach(System.out::println);
                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("shelf".equals(type)) {
                        List<List<String>> collect =
                                objectList2.stream()
                                        .filter(list -> "shelf".equals(list.get(5)) || "sub_shelf".equals(list.get(5)))
                                        .collect(Collectors.toList());
                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("slot".equals(type)) {
                        List<List<String>> collect =
                                objectList2.stream()
                                        .filter(list -> "slot".equals(list.get(5)) || "sub_slot".equals(list.get(5)))
                                        .collect(Collectors.toList());
                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("ptp".equals(type)) {
                        List<List<String>> collect =
                                objectList2.stream()
                                        .filter(list -> (list.get(0).equals("6201HWCSBPRT45591c0315590efb") ))
                                        .collect(Collectors.toList());
                      //  List<List<String>> collect1 = objectList2.stream().filter(a -> !setKey.add(a.get(0))).collect(Collectors.toList());
                     //   collect1.forEach(System.out::println);
                        int size1 = collect.size();
                        total += size1;
                        System.out.println(fileName+" fileName ---"+size1);
                    }
                    if ("onu_ptp".equals(type)) {
                        List<List<String>> collect =
                                objectList2.stream()
                                        .filter(list -> (list.get(1).contains("ONU") && !list.get(1).contains("POS") && Integer.parseInt(list.get(5))!=-1 ))
                                        .collect(Collectors.toList());
                        List<String> collect1 =
                                collect.stream()
                                        .filter(list -> ("".equals(list.get(4)))).map(a->a.get(0))
                                        .collect(Collectors.toList());
                        collect1.forEach(System.out::println);
                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("onu".equals(type)) {
                        List<String> collect =objectList2.stream().map(a->String.valueOf(a.get(0))).collect(Collectors.toList());
                        collect.forEach(System.out::println);
                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("chongFu".equals(type)) {
                        List<String> collect =objectList2.stream().map(a->String.valueOf(a.get(0))).collect(Collectors.toList());
                        List<String> collect1 = collect.stream().filter(a -> !setKey.add(a)).collect(Collectors.toList());
                        collect1.forEach(System.out::println);
//                        collect.forEach(System.out::println);

                        int size1 = collect.size();
                        total += size1;
                    }
                    if ("zhiding".equals(type)) {
                        List<List<String>> collect = objectList2.stream().filter(a -> String.valueOf(a.get(1)).equals("4512HWCSBNELc2cc154056213d88")).collect(Collectors.toList());
                        collect.forEach(System.out::println);
                        int size1 = collect.size();
                        total += size1;
                    }
                } else {
                    if (map.containsKey(type)) {
                        total = total + size;
                        Integer integer = map.get(type);
                        map.put(type, integer + size);
                    } else {
                        total = total + size;
                        map.put(type, size);
                    }
                }
            }
        }
        return total;
    }

    public int doSAXReader(List<String> fileNameList, String emsID, boolean b) {
        String type = "other";
        return doSAXReader(fileNameList, emsID, b, type);
    }
    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    // 预解析文件
    public int preSAXReaderFile(SAXReader reader, String fileName) {
        try {
            if (fileName.endsWith(".zip")) {
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileName));
                zipInputStream.getNextEntry(); // 此行不能注释，不然会报Error on line 1 of document : Premature end  of file.
                reader.read(new BufferedInputStream(zipInputStream));
            } else if (fileName.endsWith(".gz")) {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new FileInputStream(fileName));
                reader.read(new BufferedInputStream(gZIPInputStream));
            } else if (fileName.endsWith(".xml")) {
                File f = new File(fileName);
                reader.read(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int String2Int(String str) {
        if (str.equals("") || "--".equals(str)) {
            return -1;
        }

        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return -1;
        }
    }

    public String getEmsId() {
        return emsId;
    }

    public void setEmsId(String emsId) {
        this.emsId = emsId;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
    }

    /**
     * 判断当前文件是否过滤重复的rmuid
     *
     * @param fileName 要过滤的文件的文件名
     * @return 需要过滤返回true，否则返回false
     */
    public boolean isCheckRepeatRmuid(String fileName) {
        if (fileName.indexOf("-NEL-") > 0
                || fileName.indexOf("_NEL_") > 0
                || fileName.indexOf("-EQH-") > 0
                || fileName.indexOf("_EQH_") > 0
                || fileName.indexOf("-CRD-") > 0
                || fileName.indexOf("_CRD_") > 0
                || fileName.indexOf("-PRT-") > 0
                || fileName.indexOf("_PRT_") > 0
                || fileName.indexOf("-TNL-") > 0
                || fileName.indexOf("_TNL_") > 0
                || fileName.indexOf("-LBS-") > 0
                || fileName.indexOf("_LBS_") > 0
                || fileName.indexOf("-PSW-") > 0
                || fileName.indexOf("_PSW_") > 0
                || fileName.indexOf("-ETH-") > 0
                || fileName.indexOf("_ETH_") > 0
                || fileName.indexOf("-TDM-") > 0
                || fileName.indexOf("_TDM_") > 0
                || fileName.indexOf("-L3I-") > 0
                || fileName.indexOf("_L3I_") > 0
                || fileName.indexOf("-TPL-") > 0
                || fileName.indexOf("_TPL_") > 0
                || fileName.indexOf("-ESP-") > 0
                || fileName.indexOf("_ESP_") > 0
                || fileName.indexOf("-L3P-") > 0
                || fileName.indexOf("_L3P_") > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
    String path = "D:\\Documents\\Tencent Files\\北京移动OTN资源V2.2.0版本样例\\北京移动OTN资源V2.2.0版本样例\\CM-OTN-NEL-1A-V2.2.0-20230228120000.xml"; // 路径
        List<String> fileNameList = new ArrayList<String>();
        GetFilesNames.getFileName(path, fileNameList);
        fileNameList.forEach(System.out::println);
        Analyser analyser = new Analyser();
        int i = analyser.doSAXReader(fileNameList, "", true, "chongFu");
        System.out.println(i);
    }

    private static String getType(String fileName) {
        String type = "other";
        if (fileName.indexOf("-OMC-") > 0 || fileName.indexOf("_OMC_") > 0) {
            type = "-OMC-";
        } else if (fileName.indexOf("-OLT-") > 0 || fileName.indexOf("_OMC_") > 0) {
            type = "-OLT-";
        } else if (fileName.indexOf("-EQH-") > 0 || fileName.indexOf("_EQH_") > 0) {
            type = "-EQH-";
        } else if (fileName.indexOf("-CRD-") > 0 || fileName.indexOf("_CRD_") > 0) {
            type = "-CRD-";
        } else if (fileName.indexOf("-PRT-") > 0 || fileName.indexOf("_PRT_") > 0) {
            type = "-PRT-";
        } else if (fileName.indexOf("-POS-") > 0 || fileName.indexOf("_POS_") > 0) {
            type = "-POS-";
        } else if (fileName.indexOf("-ONU-") > 0 || fileName.indexOf("_ONU_") > 0) {
            type = "-ONU-";
        } else if (fileName.indexOf("-TPL-") > 0 || fileName.indexOf("_TPL_") > 0) {
            type = "-TPL-";
        } else if (fileName.indexOf("-MGW-") > 0 || fileName.indexOf("_MGW_") > 0) {
            type = "-MGW-";
        } else if (fileName.indexOf("-PRG-") > 0 || fileName.indexOf("_PRG_") > 0) {
            type = "-PRG-";
        } else if (fileName.indexOf("-PTV-") > 0 || fileName.indexOf("_PTV_") > 0) {
            type = "-PTV-";
        } else if (fileName.indexOf("-VLN-") > 0 || fileName.indexOf("_VLN_") > 0) {
            type = "-VLN-";
        } else if (fileName.indexOf("-LAG-") > 0 || fileName.indexOf("_LAG_") > 0) {
            type = "-LAG-";
        }
        return type;
    }
}
