package com.example.demo.common.TestController;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    @Select("select a.objectid,\n" +
            "       a.linkname,\n" +
            "       a.city,\n" +
            "       case when a.vendor = 1 then '华为' when a.vendor = 2 then '中兴' when a.vendor = 3 then '烽火' else '' end vendor,\n" +
            "       a.source_nename,\n" +
            "       a.dest_nename,\n" +
            "       a.emsid,\n" +
            "       a.transmitmode,\n" +
            "       a.source_neid,\n" +
            "       a.dest_neid,\n" +
            "       a.source_portid,\n" +
            "       a.dest_portid,\n" +
            "       a.source_portname,\n" +
            "       a.dest_portname,\n" +
            "       a.include_ringname,\n" +
            "       b.SERVICELEVEL                                                                                       SOURCE_NE_LEVEL,\n" +
            "       d.SERVICELEVEL                                                                                       DEST_NE_LEVEL,\n" +
            "       c.RINGTYPE\n" +
            "from PF_LINK a\n" +
            "         left join me b on a.SOURCE_NEID = b.OBJECTID\n" +
            "         left join me d on a.DEST_NEID = d.OBJECTID\n" +
            "         left join pf_ptn_ring c on a.INCLUDE_RINGNAME = c.RINGNAME\n" +
            "where a.EMSID = '3507FHCS2'\n" +
            "  and a.INCLUDE_RINGNAME is not null\n" +
            "  and c.RINGTYPE is not null  ")
    List<Map<String, Object>>  selectCount(String ems);


}
