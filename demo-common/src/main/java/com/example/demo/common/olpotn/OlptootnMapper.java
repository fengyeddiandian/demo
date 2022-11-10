package com.example.demo.common.olpotn;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OlptootnMapper
 * @Author yu.zhang
 * @Description
 * @Date 2021/12/13 14:38
 **/
@Mapper
public interface OlptootnMapper {

    @Select("    select  level,\n" +
            "                    toponode_id,\n" +
            "                    aliasname,\n" +
            "                    ne_id,\n" +
            "                    toponode_model,\n" +
            "                    psub_net_id\n" +
            "    from t_trans_toponode t\n" +
            "    start with psub_net_id\n" +
            "                   in (select a.MLSN_ID\n" +
            "                       from T_TRANS_MLSN a\n" +
            "                                inner join T_TRANS_MD b on a.MD_ID = b.MD_ID\n" +
            "                       where b.EMS_ID in ('ZYOC/ZYOC_HB',\n" +
            "                                          'AcceLink/Shijiazhuang'))\n" +
            "    connect by prior toponode_id = psub_net_id")
    List<Map<String,Object>> selectCount(String ems);

    @Select("select l.a_ems_id EMSID,\n" +
            "       l.LINK_ID,\n" +
            "       ane.ne_id      ane,\n" +
            "       aptp.board_model aboardModel ,\n" +
            "       aptp.BOARD_ID  aBOARDID,\n" +
            "       aptp.PORT_ID   aptpid ,\n" +
            "       aptp.USERLABEL auserlable,\n" +
            "       zne.ne_id      zne,\n" +
            "       zptp.board_model zboardModel ,\n" +
            "       zptp.BOARD_ID  zBOARDID,\n" +
            "       zptp.PORT_ID as zptpid ," +
            "       zptp.USERLABEL zuserlable"+
            "  from t_trans_link l\n" +
            "\n" +
            "         inner join t_trans_ne ane on l.a_ne_id = ane.ne_id\n" +
            "         inner join t_trans_ne zne on l.z_ne_id = zne.ne_id\n" +
            "         inner join t_trans_port aptp on l.a_ptp_id = aptp.port_id\n" +
            "         inner join t_trans_port zptp on l.z_ptp_id = zptp.port_id\n" +
            "where a_ne_id in ( ${ne_id})\n" +
            "   or z_ne_id in ( ${ne_id} )\n" +
            "\n")
    List<Map<String,Object>> selectlink(String ne_id);





    @Select("select   ane.aliasname as anename,\n" +
            "  s.A_EMS_ID EMSID,"+
            "       ane.NE_ID  as aneid,\n" +
            "       aptp.PORT_ID as aptpid,\n" +
            "       aptp.BOARD_ID  aBOARDID,\n" +
            "       zne.aliasname as znename,\n" +
            "       zne.NE_ID  as zneid,\n" +
            "       zptp.BOARD_ID  zBOARDID,\n" +
            "       zptp.PORT_ID as zptpid " +
            " from\n" +
            "    t_snc s,\n" +
            "    t_snc_route l,\n" +
            "    t_trans_ne   ane,\n" +
            "    t_trans_ne   zne,\n" +
            "    t_trans_port aptp,\n" +
            "    t_trans_port zptp\n" +
            "where\n" +
            "    l.a_ne_id = ane.ne_id\n" +
            "  and s.snc_id = l.snc_id\n" +
            "    and l.z_ne_id = zne.ne_id\n" +
            "    and l.a_ptp_id = aptp.port_id\n" +
            "    and l.z_ptp_id = zptp.port_id\n" +
            "    and ((l.a_ne_id = #{aOtnMeId} and l.z_ne_id = #{zOtnMeId} )\n" +
            "             or (l.z_ne_id = #{aOtnMeId} and l.a_ne_id = #{zOtnMeId} ))\n" +
            "and s.layer_rate = '41'")
    List<Map<String,Object>> selectOtnLink(String aOtnMeId, String zOtnMeId);


    @Select(" select ne_id neId from t_trans_ne where aliasname in ( #{aOtnMeName} ,#{zOtnMeName} )")
    List<Map<String,Object>> selectOtnMe(String aOtnMeName, String zOtnMeName);

    @Insert("insert into t_trans_link_OLP_COPY (LINK_ID, A_NE_ID, A_PTP_ID, Z_NE_ID, Z_PTP_ID, A_EMS_ID, Z_EMS_ID, DIRECTION, LINK_TYPE,  DATA_SOURCE,      USERLABEL, ALIASNAME,  INSERT_TIME,    LAYER_RATE)  VALUES" +
            "( #{linkId} ,  #{aNeId} ,  #{aPtpId} ,  #{zNeId} ,  #{zPtpId} ,  #{aEmsId} ,  #{zEmsId} ,  #{direction} ,  #{linkType} ,  #{dataSource} ,  #{userlabel} ,  #{aliasname} ,  #{insertTime} ,  #{layerRate}    ) ")
    void insertData(TransLink dto);
}
