package com.example.demo.common.xmlutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

public class FieldValueElementHandler implements ElementHandler
{
    private List<List<String>> objectList = null;
    private HashMap<String, String> repeatRmuidKeyMap = null;
    private boolean isCheckRepratRmuid = false;
    private HashMap<String, String> rmuidKeyMap = new HashMap<String, String>();
    
    public FieldValueElementHandler()
    {

    }

    @Override
    public void onEnd(ElementPath arg0)
    {
        objectList = new ArrayList<List<String>>();
        repeatRmuidKeyMap = new HashMap<String, String>();
        
        Element ele = arg0.getCurrent();
        List<Element> list = ele.elements();
        
        for (Element node : list)
        {
            // rmUID是所有信息模型的第一个属性，没有定义在V标签中，而是定义在Object标签的属性中
            String rmUID = node.attributeValue("rmUID");
            //增量采集操作对象
            String operationType = node.attributeValue("OperationType");
            
            //检查是否有重的rmUID
            if(isCheckRepratRmuid){
                if(rmuidKeyMap.get(rmUID) == null){
                    rmuidKeyMap.put(rmUID, "");
                }else{
                    repeatRmuidKeyMap.put(rmUID, "");
                    continue;
                }
            }
            
            List<String> vList = new ArrayList<String>();
            objectList.add(vList);
            
            vList.add(rmUID);
            
            //增量采集操作对象
            if(operationType != null){
            	vList.add(operationType);
            }
            
            List<Element> list2 = node.elements();
            for (Element subNode : list2)
            {
                vList.add(subNode.getText());
            }
        }

        ele.detach();
        isCheckRepratRmuid = false;
    }

    @Override
    public void onStart(ElementPath arg0)
    {

    }

    public List<List<String>> getObjectList()
    {
        return objectList;
    }

    public void setObjectList(List<List<String>> objectList)
    {
        this.objectList = objectList;
    }
    
    public HashMap<String, String> getReapeatRmuidMap()
    {
        return repeatRmuidKeyMap;
    }

    public boolean isCheckRepratRmuid() {
        return isCheckRepratRmuid;
    }

    public void setCheckRepratRmuid(boolean isCheckRepratRmuid) {
        this.isCheckRepratRmuid = isCheckRepratRmuid;
    }
    
    public HashMap<String, String> getRmuidKeyMap() {
		return rmuidKeyMap;
	}

	public void setRmuidKeyMap(HashMap<String, String> rmuidKeyMap) {
		this.rmuidKeyMap = rmuidKeyMap;
	}
}
