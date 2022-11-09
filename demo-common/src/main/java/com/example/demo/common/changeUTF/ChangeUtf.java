package com.example.demo.common.changeUTF;

import java.io.*;

/** @ClassName ChangeUtf @Author yu.zhang @Description @Date 2021/12/2 10:34 */
public class ChangeUtf {

    /**
     * @param inputFileUrl
     * @param outputFileUrl
     * @throws IOException
     */
    public static void saveAsUTF8(String inputFileUrl, String outputFileUrl) throws IOException {
//        String inputFileEncode = EncodingDetect.getJavaEncode(inputFileUrl);
//        System.out.println("inputFileEncode===" + inputFileEncode);
//        BufferedReader bufferedReader =
//                new BufferedReader(
//                        new InputStreamReader(new FileInputStream(inputFileUrl), inputFileEncode));
//        BufferedWriter bufferedWriter =
//                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileUrl), "UTF-8"));
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            bufferedWriter.write(line + "\r\n");
//        }
//        bufferedWriter.close();
//        bufferedReader.close();
//        String outputFileEncode = EncodingDetect.getJavaEncode(outputFileUrl);
//        System.out.println("outputFileEncode===" + outputFileEncode);
//        System.out.println("txt文件格式转换完成");



    }

    public static void main(String[] args) {

//        Map<String,List<String>> map = new HashMap<>();
//        List<String> list1 = new ArrayList<>();
//        list1.add("EFR-1");
//        list1.add("EFR-2");
//        list1.add("EFR-1");
//        map.put("aa",list1);
//
//        List<String> list2 = new ArrayList<>();
//        list2.add("EFR-2");
//        list2.add("EFR-1");
//
//
//        map.put("bb",list2);
//        List<String> collect3 = map.keySet().stream().filter(a -> map.get(a).size() >1).collect(Collectors.toList());
//        Set<String> collect1 = collect3.stream().map(a ->
//                map.get(a).stream().map(b -> "'" + b + "'").collect(Collectors.joining(","))
//        ).collect(Collectors.toSet());
//
//        List<String> collect2 = collect3.stream().map(a ->
//                map.get(a).stream().map(b -> "'" + b + "'").sorted().collect(Collectors.joining(","))
//        ).collect(Collectors.toList());
//        collect1.forEach(System.out::println);
//        collect2.forEach(System.out::println);

    }
}
