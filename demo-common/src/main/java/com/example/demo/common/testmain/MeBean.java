package com.example.demo.common.testmain;

import lombok.Data;

@Data
public class MeBean {
	private String ringObjectId;
    private String meObjectId;
    private String mename;
    private String model;
    private String serviceLevel;
    private String vendor;
    private String jifangName;
    private String juzhanName;
    private String emsObjectId;
    private String emsName;
    private int meIndex;

	public MeBean(String ringObjectId, String meObjectId, String mename) {
		this.ringObjectId = ringObjectId;
		this.meObjectId = meObjectId;
		this.mename = mename;
	}
}
