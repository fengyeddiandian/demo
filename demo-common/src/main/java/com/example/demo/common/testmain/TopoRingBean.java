package com.example.demo.common.testmain;

import java.util.ArrayList;
import java.util.List;

public class TopoRingBean {
    private String objectId;
    private String emsObjectId;
    private String emsname;
    private String ringname;
    private String ringRate;
    private String ringRank;
    private int ringMeCount;
    private int isDualHoming;
    private String notAccessMeObjectId;
    
    private List<MeBean> meList;
    private List<TopoLinkBean> linkList;
    
    
    
	public String getNotAccessMeObjectId() {
		return notAccessMeObjectId;
	}
	public void setNotAccessMeObjectId(String notAccessMeObjectId) {
		this.notAccessMeObjectId = notAccessMeObjectId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getEmsObjectId() {
		return emsObjectId;
	}
	public void setEmsObjectId(String emsObjectId) {
		this.emsObjectId = emsObjectId;
	}
	public String getEmsname() {
		return emsname;
	}
	public void setEmsname(String emsname) {
		this.emsname = emsname;
	}
	public String getRingname() {
		return ringname;
	}
	public void setRingname(String ringname) {
		this.ringname = ringname;
	}
	public String getRingRate() {
		return ringRate;
	}
	public void setRingRate(String ringRate) {
		this.ringRate = ringRate;
	}
	public String getRingRank() {
		return ringRank;
	}
	public void setRingRank(String ringRank) {
		this.ringRank = ringRank;
	}
	public int getRingMeCount() {
		return ringMeCount;
	}
	public void setRingMeCount(int ringMeCount) {
		this.ringMeCount = ringMeCount;
	}
	public int getIsDualHoming() {
		return isDualHoming;
	}
	public void setIsDualHoming(int isDualHoming) {
		this.isDualHoming = isDualHoming;
	}
	public List<MeBean> getMeList() {
		if(meList==null) {
			meList = new ArrayList<MeBean>();
		}
		return meList;
	}
	public void setMeList(List<MeBean> meList) {
		this.meList = meList;
	}
	public List<TopoLinkBean> getLinkList() {
		if(linkList==null) {
			linkList = new ArrayList<TopoLinkBean>();
		}
		return linkList;
	}
	public void setLinkList(List<TopoLinkBean> linkList) {
		this.linkList = linkList;
	}
    
    
}
