/**
 * This file is auto generated by tto v0.5.1 !
 * If you want to modify this code, please read the guide 
 * to modify code template.
 *
 * Get started: https://github.com/ixre/tto
 *
 * Copyright (C) 2009-2022 FZE.NET, All rights reserved.
 *
 * name : AxqStaffServiceImpl.java
 * author : jarrysix
 * date : 2022/10/18 10:23:35
 * description :
 * history :
 */

package com.github.ixre.tto.demo.service.axq.impl;

import com.github.ixre.tto.demo.entity.AxqStaffEntity;
import com.github.ixre.tto.demo.repo.AxqStaffJpaRepository;
import com.github.ixre.tto.demo.service.axq.IAxqStaffService;
import net.fze.util.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import net.fze.common.infrastructure.PagingResult;
import net.fze.common.Standard;
import net.fze.util.Times;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;



/** AxqStaff服务  */
@Service("axq_staff_service")
public class AxqStaffServiceImpl implements IAxqStaffService{
    @Autowired
    private AxqStaffJpaRepository repo;

    /** 查找AxqStaff */
    @Override
    public AxqStaffEntity findStaffById(int id){
        return this.repo.findById(id).orElse(null);
    }

    /** 查找全部AxqStaff */
    @Override
    public List<AxqStaffEntity> findAllStaff() {
        return this.repo.findAll();
    }

    /** 保存AxqStaff */
    @Override
    public Error saveStaff(AxqStaffEntity e){
         return Standard.tryCatch(()-> {
            AxqStaffEntity dst;
            if (e.getId() > 0) {
                dst = this.repo.findById(e.getId()).orElse(null);
                if(dst == null)throw new IllegalArgumentException("no such data");
            } else {
                dst = AxqStaffEntity.createDefault();
                dst.setCreateTime(new java.util.Date());
            }
            dst.setDeptId(Types.orValue(e.getDeptId(),0));
            dst.setJobId(Types.orValue(e.getJobId(),0));
            dst.setIsAdmin(Types.orValue(e.getIsAdmin(),0));
            dst.setUsername(e.getUsername());
            dst.setPasswd(e.getPasswd());
            dst.setNickname(e.getNickname());
            dst.setMobile(e.getMobile());
            dst.setAvatar(e.getAvatar());
            dst.setStatus(Types.orValue(e.getStatus(),0));
            dst.setIsDelete(Types.orValue(e.getIsDelete(),0));
            dst.setDeleteTime(Types.orValue(e.getDeleteTime(),new Date()));
            dst.setUpdateTime(Types.orValue(new java.util.Date(),new Date()));
            dst = this.repo.save(dst);
            e.setId(dst.getId());
            return null;
          }).except(it->{
            it.printStackTrace();
            return null;
         }).error();
    }

    /** 根据对象条件查找 */
    @Override
    public AxqStaffEntity findStaffBy(AxqStaffEntity o){
        return this.repo.findOne(Example.of(o)).orElse(null);
    }

    /** 根据对象条件查找 */
    @Override
    public List<AxqStaffEntity> findStaffListBy(AxqStaffEntity o) {
        return this.repo.findAll(Example.of(o));
    }

    /** 根据条件分页查询 */
    @Override
    public PagingResult<AxqStaffEntity> findPagingStaff(AxqStaffEntity o, Pageable page) {
        Page<AxqStaffEntity> p = this.repo.findAll(Example.of(o),page);
        return new PagingResult<AxqStaffEntity>(p.getContent(),p.getTotalElements());
    }

    /** 批量保存AxqStaff */
    @Override
    public Iterable<AxqStaffEntity> saveAllStaff(Iterable<AxqStaffEntity> entities){
        return this.repo.saveAll(entities);
    }

    /** 删除AxqStaff */
    @Override
    public Error deleteStaffById(int id) {
         return Standard.tryCatch(()-> {
             this.repo.deleteById(id);
             return null;
           }).except(it->{
            it.printStackTrace();
            return null;
         }).error();
    }

    /** 批量删除AxqStaff */
    @Override
    public Error batchDeleteStaff(List<Integer> id){
        return Standard.tryCatch(() -> {
            List<AxqStaffEntity> all = this.repo.findAllById(id);
            this.repo.deleteInBatch(all);
            return null;
        }).except(it -> {
            it.printStackTrace();
            return null;
        }).error();
    }
}