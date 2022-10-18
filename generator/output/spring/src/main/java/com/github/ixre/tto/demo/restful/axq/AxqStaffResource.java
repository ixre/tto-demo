/**
 * This file is auto generated by tto v0.5.1 !
 * If you want to modify this code, please read the guide 
 * to modify code template.
 *
 * Get started: https://github.com/ixre/tto
 *
 * Copyright (C) 2009-2022 FZE.NET, All rights reserved.
 *
 * name : AxqStaffResource.java
 * author : jarrysix
 * date : 2022/10/18 14:03:28
 * description :
 * history :
 */

package com.github.ixre.tto.demo.restful;

import com.github.ixre.tto.demo.entity.AxqStaffEntity;
import com.github.ixre.tto.demo.service.axq.IAxqStaffService;
import com.github.ixre.tto.demo.component.ReportDataSource;
import net.fze.annotation.Resource;
import net.fze.common.Result;
import net.fze.common.Standard;
import net.fze.extras.report.DataResult;
import net.fze.extras.report.ReportUtils;
import net.fze.extras.report.Params;
import net.fze.util.Strings;
import net.fze.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;

/* AxqStaff资源 */
@RestController
@RequestMapping("/api/axq/staff")
public class AxqStaffResource {
    @Inject private IAxqStaffService service;
    @Inject private ReportDataSource reportDs;

    /** 获取AxqStaff */
    @GetMapping("/{id}")
    @Resource(key = "axq:staff:get",name="获取AxqStaff")
    public AxqStaffEntity getStaff(@PathVariable("id") int id){
        return this.service.findStaffById(id);
    }

    /** 创建AxqStaff */
    @PostMapping
    @Resource(key = "axq:staff:create",name="创建AxqStaff")
    public Result createStaff(@RequestBody AxqStaffEntity entity){
        Error err = Standard.catchError(()->{
            this.validateStaff(entity);
            return this.service.saveStaff(entity);
        });
        return Result.of(err);
    }

    /** 更新AxqStaff */
    @PutMapping("/{id}")
    @Resource(key = "axq:staff:update",name="更新AxqStaff")
    public Result updateStaff(@PathVariable("id") int id,@RequestBody AxqStaffEntity entity) {
        Error err = Standard.catchError(()->{
            entity.setId(id);
            this.validateStaff(entity);
            return this.service.saveStaff(entity);
        });
        return Result.of(err);
    }

    /** 验证实体 */
    private void validateStaff(AxqStaffEntity e){
    }

    /** 删除AxqStaff */
    @DeleteMapping("/{id}")
    @Resource(key = "axq:staff:delete",name="删除AxqStaff")
    public Result deleteStaff(@PathVariable("id") int id){
        Error err = this.service.deleteStaffById(id);
        return Result.of(err);
    }

    /** AxqStaff分页数据 */
    @GetMapping("/paging")
    @Resource(key = "axq:staff:paging",name="查询AxqStaff分页数据")
    public DataResult pagingStaff(@RequestParam(name ="params",defaultValue = "{}", required = false) String params,
               @RequestParam("page") String page,
               @RequestParam("rows") String rows){
        Params p = ReportUtils.parseParams(params);
        //String timeRangeSQL = ReportUtils.timeRangeSQLByJSONTime(p.get("create_time"), "create_time");
        //p.set("create_time", timeRangeSQL);
        return this.reportDs.fetchData("default",
                "axq/staff_list", p, page, rows);
    }

    /** 查询AxqStaff列表 */
    @GetMapping
    @Resource(key = "axq:staff:list",name="查询AxqStaff")
    public List<AxqStaffEntity> queryStaff(@RequestParam(name="params",defaultValue="{}") String params) {
        //val p = ReportUtils.parseParams(params).getValue();
        return this.service.findAllStaff();
    }

    /** 批量删除AxqStaff */
    @DeleteMapping("")
    @Resource(key = "axq:staff:delete",name="删除AxqStaff")
    public Result batchDeleteStaff(@RequestBody List<Integer> id){
        if(id.isEmpty())return Result.error(2,"没有要删除的行");
        Error err = this.service.batchDeleteStaff(id);
        return Result.of(err);
    }
}