package com.example.demo.bl.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Sc4aUserService",targetNamespace = "http://service.scyd.staffuser.web.tomp.tnms.zznode.com")
public interface Sc4aUserService {

    @WebMethod
    String findUser(String userID);
    
//    @WebMethod
//    public String insertUser(String userInfos);
//
//    @WebMethod
//    public String upUser(String userInfos);
//
//    @WebMethod
//    public String delUser(String account);
}
