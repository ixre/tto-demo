/**
 * This file is auto generated by tto v0.5.1 !
 * If you want to modify this code, please read the guide 
 * to modify code template.
 *
 * Get started: https://github.com/ixre/tto
 *
 * Copyright (C) 2009-2022 FZE.NET, All rights reserved.
 *
 * name : AxqStaffLoginLog.java
 * author : jarrysix
 * date : 2022/10/18 14:03:28
 * description :
 * history :
 */

package com.github.ixre.tto.demo.entity;

import net.fze.util.TypeConv;
import java.math.BigDecimal;
import java.util.Date;

/** AxqStaffLoginLog */
public class AxqStaffLoginLog {
    private int id; // Id
    private String username; // Username
    private String passwd; // Passwd
    private String ip; // Ip
    private int isDelete; // IsDelete
    private Date createTime; // CreateTime
    private Date updateTime; // UpdateTime
    private Date deleteTime; // DeleteTime
    
    
    public AxqStaffLoginLog setId(int id){
        this.id = id;
        return this;
    }

    /** Id */
    public int getId() {
        return this.id;
    }
    
    public AxqStaffLoginLog setUsername(String username){
        this.username = username;
        return this;
    }

    /** Username */
    public String getUsername() {
        return this.username;
    }
    
    public AxqStaffLoginLog setPasswd(String passwd){
        this.passwd = passwd;
        return this;
    }

    /** Passwd */
    public String getPasswd() {
        return this.passwd;
    }
    
    public AxqStaffLoginLog setIp(String ip){
        this.ip = ip;
        return this;
    }

    /** Ip */
    public String getIp() {
        return this.ip;
    }
    
    public AxqStaffLoginLog setIsDelete(int isDelete){
        this.isDelete = isDelete;
        return this;
    }

    /** IsDelete */
    public int getIsDelete() {
        return this.isDelete;
    }
    
    public AxqStaffLoginLog setCreateTime(Date createTime){
        this.createTime = createTime;
        return this;
    }

    /** CreateTime */
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public AxqStaffLoginLog setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
        return this;
    }

    /** UpdateTime */
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public AxqStaffLoginLog setDeleteTime(Date deleteTime){
        this.deleteTime = deleteTime;
        return this;
    }

    /** DeleteTime */
    public Date getDeleteTime() {
        return this.deleteTime;
    }
    

    
    public static AxqStaffLoginLog createDefault(){
        AxqStaffLoginLog dst = new AxqStaffLoginLog();
        dst.setId(0);
        dst.setUsername("");
        dst.setPasswd("");
        dst.setIp("");
        dst.setIsDelete(0);
        dst.setCreateTime(new Date());
        dst.setUpdateTime(new Date());
        dst.setDeleteTime(new Date());
        return dst;
    }
}