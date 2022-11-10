package com.example.demo.common.MD5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnuPtpMd5Info{
	private  String  objectId;
	private  String  ptpId;
	private  String  nativeName;
	private  String  cardObjectId;
	private  String  meObjectId;
	private  int  ptpNumber;
	private  String  ptpType;
	private  String  ptpState;
	private  String  adminState;
	private  int  isUplinkPort;
	private  String  objectKey;
	private  String  emsObjectId;

}
