package com.example.demo.common.xmlutil;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.Node;

public class FieldNameElementHandler implements ElementHandler
{
    private List<String> keyList = null;

    public FieldNameElementHandler()
    {

    }

    @Override
    public void onEnd(ElementPath arg0)
    {
        keyList = new ArrayList<String>();
        
        // rmUID不在FieldName标签内，单独添加
        keyList.add("rmUID");
        
        Element ele = arg0.getCurrent();
        List<Node> list = ele.selectNodes("./N");
        for (Node node : list)
        {
            keyList.add(node.getText());
        }

        ele.detach();
    }

    @Override
    public void onStart(ElementPath arg0)
    {

    }

    public List<String> getKeyList()
    {
        return keyList;
    }

    public void setKeyList(List<String> keyList)
    {
        this.keyList = keyList;
    }
}
