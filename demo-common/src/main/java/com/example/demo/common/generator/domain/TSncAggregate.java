package com.example.demo.common.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName T_SNC_AGGREGATE
 */
@TableName(value ="T_SNC_AGGREGATE")
@Data
public class TSncAggregate implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String sncId;

    /**
     * 
     */
    private String circuitName;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String aMacPortName;

    /**
     * 
     */
    private String aMacPortUserlabel;

    /**
     * 
     */
    private String aMacPortId;

    /**
     * 
     */
    private String aVctrunkPortName;

    /**
     * 
     */
    private String aVctrunkPortUserlabel;

    /**
     * 
     */
    private String aVctrunkPortId;

    /**
     * 
     */
    private String aliasname;

    /**
     * 
     */
    private String sncAportName;

    /**
     * 
     */
    private String sncAportUserlabel;

    /**
     * 
     */
    private String sncAportId;

    /**
     * 
     */
    private String sncAtimeName;

    /**
     * 
     */
    private String sncZportName;

    /**
     * 
     */
    private String sncZportUserlabel;

    /**
     * 
     */
    private String sncZportId;

    /**
     * 
     */
    private String sncZtimeName;

    /**
     * 
     */
    private String zMacPortName;

    /**
     * 
     */
    private String zMacPortUserlabel;

    /**
     * 
     */
    private String zMacPortId;

    /**
     * 
     */
    private String zVctrunkPortName;

    /**
     * 
     */
    private String zVctrunkPortUserlabel;

    /**
     * 
     */
    private String zVctrunkPortId;

    /**
     * 
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}