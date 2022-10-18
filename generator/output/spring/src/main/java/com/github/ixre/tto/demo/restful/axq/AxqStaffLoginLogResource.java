/**
 * This file is auto generated by tto v0.5.1 !
 * If you want to modify this code, please read the guide 
 * to modify code template.
 *
 * Get started: https://github.com/ixre/tto
 *
 * Copyright (C) 2009-2022 FZE.NET, All rights reserved.
 *
 * name : AxqStaffLoginLogResource.java
 * author : jarrysix
 * date : 2022/10/18 14:03:28
 * description :
 * history :
 */

package com.github.ixre.tto.demo.restful;

import com.github.ixre.tto.demo.entity.AxqStaffLoginLogEntity;
import com.github.ixre.tto.demo.service.axq.IAxqStaffLoginLogService;
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

/* AxqStaffLoginLog资源 */
@RestController
@RequestMapping("/api/axq/staff_login_log")
public class AxqStaffLoginLogResource {
    @Inject private IAxqStaffLoginLogService service;
    @Inject private ReportDataSource reportDs;

    /** 获取AxqStaffLoginLog */
    @GetMapping("/{id}")
    @Resource(key = "axq:staff_login_log:get",name="获取AxqStaffLoginLog")
    public AxqStaffLoginLogEntity getStaffLoginLog(@PathVariable("id") int id){
        return this.service.findStaffLoginLogById(id);
    }

    /** 创建AxqStaffLoginLog */
    @PostMapping
    @Resource(key = "axq:staff_login_log:create",name="创建AxqStaffLoginLog")
    public Result createStaffLoginLog(@RequestBody AxqStaffLoginLogEntity entity){
        Error err = Standard.catchError(()->{
            this.validateStaffLoginLog(entity);
            return this.service.saveStaffLoginLog(entity);
        });
        return Result.of(err);
    }

    /** 更新AxqStaffLoginLog */
    @PutMapping("/{id}")
    @Resource(key = "axq:staff_login_log:update",name="更新AxqStaffLoginLog")
    public Result updateStaffLoginLog(@PathVariable("id") int id,@RequestBody AxqStaffLoginLogEntity entity) {
        Error err = Standard.catchError(()->{
            entity.setId(id);
            this.validateStaffLoginLog(entity);
            return this.service.saveStaffLoginLog(entity);
        });
        return Result.of(err);
    }

    /** 验证实体 */
    private void validateStaffLoginLog(AxqStaffLoginLogEntity e){
    }

    /** 删除AxqStaffLoginLog */
    @DeleteMapping("/{id}")
    @Resource(key = "axq:staff_login_log:delete",name="删除AxqStaffLoginLog")
    public Result deleteStaffLoginLog(@PathVariable("id") int id){
        Error err = this.service.deleteStaffLoginLogById(id);
        return Result.of(err);
    }

    /** AxqStaffLoginLog分页数据 */
    @GetMapping("/paging")
    @Resource(key = "axq:staff_login_log:paging",name="查询AxqStaffLoginLog分页数据")
    public DataResult pagingStaffLoginLog(@RequestParam(name ="params",defaultValue = "{}", required = false) String params,
               @RequestParam("page") String page,
               @RequestParam("rows") String rows){
        Params p = ReportUtils.parseParams(params);
        //String timeRangeSQL = ReportUtils.timeRangeSQLByJSONTime(p.get("create_time"), "create_time");
        //p.set("create_time", timeRangeSQL);
        return this.reportDs.fetchData("default",
                "axq/staff_login_log_list", p, page, rows);
    }

    /** 查询AxqStaffLoginLog列表 */
    @GetMapping
    @Resource(key = "axq:staff_login_log:list",name="查询AxqStaffLoginLog")
    public List<AxqStaffLoginLogEntity> queryStaffLoginLog(@RequestParam(name="params",defaultValue="{}") String params) {
        //val p = ReportUtils.parseParams(params).getValue();
        return this.service.findAllStaffLoginLog();
    }

    /** 批量删除AxqStaffLoginLog */
    @DeleteMapping("")
    @Resource(key = "axq:staff_login_log:delete",name="删除AxqStaffLoginLog")
    public Result batchDeleteStaffLoginLog(@RequestBody List<Integer> id){
        if(id.isEmpty())return Result.error(2,"没有要删除的行");
        Error err = this.service.batchDeleteStaffLoginLog(id);
        return Result.of(err);
    }
}