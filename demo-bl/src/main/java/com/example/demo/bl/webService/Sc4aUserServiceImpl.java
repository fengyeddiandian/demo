package com.example.demo.bl.webService;

import cn.hutool.core.util.ObjectUtil;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "Sc4aUserService",
        targetNamespace = "http://service.scyd.staffuser.web.tomp.tnms.zznode.com",
                   endpointInterface = "com.example.demo.bl.webService.Sc4aUserService"
)
@Service("Sc4aUserServiceImpl")
public class Sc4aUserServiceImpl implements Sc4aUserService {
    private static Logger logger = LoggerFactory.getLogger(Sc4aUserServiceImpl.class);

//    @Autowired
//    private Sc4aUserDao dao;

    /**
     * [根据账号查询4a注册用户信息]
     *
     * @param userID
     * @return
     */
    
    @Override
    public String findUser(String userID) {
        String result = "";
        try {
//            result = getXml(dao.findUser(userID,null));
            result ="123123";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取4a单个用户信息异常",e);
        }
        return result;
    }
    
    
    /**
     * 【添加用户】
     * @param userInfos
     * @return
     */
//    @Override
//    public String insertUser(String userInfos) {
//        String returnMsg = "";
//        try {
//            Document document = DocumentHelper.parseText(userInfos);
//            Element element = document.getRootElement();
//            List<Element> elements = element.elements();
//            ArrayList<String> accounts = new ArrayList<>();
//            String errorMesg = "";
//            for (int i = 0; i < elements.size(); i++) {
//                Element node = elements.get(i);
//                String account = node.element("accId").getText();// 账号
//                String name = node.element("sn").getText()+node.element("name").getText();// 姓名
//                String employeeNumber = node.element("employeeNumber").getText();// 工号
//                String gender = node.element("gender").getText();// 性别  男1 女2 未知3
//                String telephome = node.element("telephoneNumber").getText();// 电话号码
//                String mobile = node.element("mobile").getText();// 移动电话
//                String startTime = node.element("startTime").getText();// 用户开始有效时间
//                String endTime = node.element("endTime").getText();// 用户结束有效时间
//                List<User> user = null;
//                try {
//                    user =dao.findUser(node.element("accId").getText(), node.element("mobile").getText());
//                } catch (Exception e) {
//                    logger.error("查询4a用户异常",e);
//                }
//                if (user.size() > 0) {
//                    // 当前账号存在
//                    accounts.add(account);
//                    errorMesg +="账号【"+account+"】或手机号【"+mobile+"】已存在；";
//                    continue;
//                }else{
//                    try {
//                        dao.insertUser(account,name,employeeNumber,gender,telephome,mobile,startTime,endTime);
//                    } catch (Exception e) {
//                        logger.error("添加4a用户异常",e);
//                        accounts.add(account);
//                        errorMesg +="账号【"+account+"】添加异常；";
//                    }
//                }
//
//
//            }
//            returnMsg = saveXml(accounts, errorMesg,"1");
//        } catch (DocumentException e) {
//            logger.error("添加用户信息异常",e);
//        }
//        return returnMsg;
//    }
    
    
//    /**
//     * 【添加用户】
//     * @param userInfos
//     * @return
//     */
//    @Override
//    public String upUser(String userInfos) {
//        String returnMsg = "";
//        try {
//            Document document = DocumentHelper.parseText(userInfos);
//            Element element = document.getRootElement();
//            List<Element> elements = element.elements();
//            ArrayList<String> accounts = new ArrayList<>();
//            String errorMesg = "";
//            for (int i = 0; i < elements.size(); i++) {
//                Element node = elements.get(i);
//                String account = node.element("accId").getText();// 账号
//                String name = node.element("sn").getText()+node.element("name").getText();// 姓名
//                String employeeNumber = node.element("employeeNumber").getText();// 工号
//                String gender = node.element("gender").getText();// 性别  男1 女2 未知3
//                String telephome = node.element("telephoneNumber").getText();// 电话号码
//                String mobile = node.element("mobile").getText();// 移动电话
//                String startTime = node.element("startTime").getText();// 用户开始有效时间
//                String endTime = node.element("endTime").getText();// 用户结束有效时间
//                List<User> user = null;
//                try {
//                    user =dao.findUser(node.element("accId").getText(), node.element("mobile").getText());
//                } catch (Exception e) {
//                    logger.error("查询4a用户异常",e);
//                }
//                if (user.size() == 1) {
//                    // 修改账号信息
//                    try {
//                        dao.upUser(account,name,employeeNumber,gender,telephome,mobile,startTime,endTime);
//                    } catch (Exception e) {
//                        logger.error("修改4a用户异常",e);
//                        accounts.add(account);
//                        errorMesg +="账号【"+account+"】修改异常；";
//                    }
//
//
//                }else{
//                    // 当前账号存在
//                    accounts.add(account);
//                    errorMesg +="账号【"+account+"】或手机号【"+mobile+"】存在'"+user.size()+"'个；";
//                    continue;
//                }
//
//
//            }
//            returnMsg = saveXml(accounts, errorMesg,"1");
//        } catch (DocumentException e) {
//            logger.error("添加用户信息异常",e);
//        }
//        return returnMsg;
//    }
//
//    @Override
//    public String delUser(String account) {
//        String returnMsg = "";
//        if (account != null) {
//            ArrayList<String> accounts = new ArrayList<>();
//            String errorMesg = "";
//            Document document = null;
//            try {
//                document = DocumentHelper.parseText(account);
//            } catch (DocumentException e) {
//                logger.error("生成xml异常",e);
//            }
//            Element element = document.getRootElement();
//            System.out.println(element.getName());
////            System.out.println(element.getName());
//            List<Element> elements = element.elements();
//
//            for (int i = 0; i < elements.size(); i++) {
//                Node node = elements.get(i);
//
//                try {
//                    dao.delUser(node.getText());
//                } catch (Exception e) {
//                    logger.error("删除用户异常【"+node.getText()+"】",e);
//                    accounts.add(node.getText());
//                    errorMesg += "删除账号【" + node.getText() + "】异常；";
//                }
//            }
//            returnMsg = saveXml(accounts,errorMesg,"0");
//        }
//        return returnMsg;
//    }
    
    
    
//    public String saveXml(ArrayList<String> accounts,String errorMesg,String flag) {
//        StringBuffer sbr = new StringBuffer();
//        sbr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        sbr.append("<results>");
//        if (accounts.size() > 0) {
//            if (flag.equals("1")) {
//                sbr.append("<result returncode=\"1301\">");
//            } else {
//                sbr.append("<result returncode=\"1305\">");
//            }
//
//            for (String accId : accounts) {
//                sbr.append("<accId>");
//                sbr.append(accId);
//                sbr.append("</accId>");
//            }
//            sbr.append("</result>");
//            sbr.append("<errorMsg>");
//            sbr.append(errorMesg);
//            sbr.append("</errorMsg>");
//        }
//        sbr.append("</results>");
//        return sbr.toString();
//    }
//
    /**
     * 【返回查询的xml格式的工具】
     * @param list
     * @return
//     */
//    public String getXml(List<User> list) {
//        StringBuffer sbr = new StringBuffer();
//        sbr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        sbr.append("<accounts>");
//        if (ObjectUtil.isNotEmpty(list)) {
//            for (User user: list) {
//                sbr.append("<account>");
//                sbr.append("<accId>"+user.getStaff_account()+"</accId>");
//                sbr.append("<name>"+user.getStaff_name().substring(1,user.getStaff_name().length())+"</name>");
//                sbr.append("<sn>"+user.getStaff_name().substring(0,1)+"</sn>");
//                sbr.append("<description></description>");
//                sbr.append("<email>"+user.getEmail()+"</email>");
//                sbr.append("<gender>"+user.getSex()+"</gender>");
//                sbr.append("<telephoneNumber>"+user.getTelphone()+"</telephoneNumber>");
//                sbr.append("<mobile>"+user.getMobile()+"</mobile>");
//                sbr.append("<startTime>"+ user.getAvail_begin_date()+"</startTime>");
//                sbr.append("<endTime>"+ user.getAvail_end_date()+"</endTime>");
//                sbr.append("<idCardNumber></idCardNumber>");
//                sbr.append("<employeeNumber></employeeNumber>");
//                sbr.append("<o></o>");
//                sbr.append("<employeeType></employeeType>");
//                sbr.append("<supporterCorpName></supporterCorpName>");
//                sbr.append("</account>");
//            }
//
//        }
//        sbr.append("</accounts>");
//        return sbr.toString();
//    }
    
}
