package com.rapidboot.demo.controller;


import com.github.pagehelper.PageInfo;
import com.rapidboot.common.annotation.AnonymousAccess;
import com.rapidboot.common.persistence.BatchDTO;
import com.rapidboot.common.result.CommonResponse;
import com.rapidboot.common.result.DetailResponse;
import com.rapidboot.common.result.PageResponse;
import com.rapidboot.common.result.ResponseUtil;
import com.rapidboot.demo.domain.LoginAppHistory;
import com.rapidboot.demo.dto.LoginAppHistoryQueryDTO;
import com.rapidboot.demo.service.LoginAppHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 广峰 <gf@gfzj.us>
 * @date 2022-01-29 02:17
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "这个是很牛逼的接口，其实直接引用 table 的介绍就行了,先偷懒管理")
@RequestMapping("/api/loginAppHistory")
public class LoginAppHistoryController {

    @Autowired
    private LoginAppHistoryService loginAppHistoryService;

    /**
     * 列表查询
     */
    @ApiOperation(value = " 列表查询")
    @GetMapping("list")
    @AnonymousAccess
    public PageResponse<LoginAppHistory> list(LoginAppHistoryQueryDTO queryDTO) {
        PageInfo<LoginAppHistory> pageInfo = loginAppHistoryService.findPage(queryDTO);
        PageResponse<LoginAppHistory> pageResponse = ResponseUtil.pageSuccess(pageInfo);
        return pageResponse;
    }

    /**
     * 保存或修改
     */
    @ApiOperation(value = " 保存或修改")
    @PostMapping("/save")
    @AnonymousAccess
    public DetailResponse<LoginAppHistory> save(@RequestBody LoginAppHistory loginAppHistory) {
        loginAppHistoryService.save(loginAppHistory);
        DetailResponse<LoginAppHistory> success = ResponseUtil.detailSuccess(loginAppHistory);
        return success;
    }


    /**
     * 详情
     */
    @ApiOperation("详情")
    @GetMapping("/detail/{loginAppHistoryId}")
    @AnonymousAccess
    public DetailResponse<LoginAppHistory> detail(@PathVariable Long loginAppHistoryId) {
        LoginAppHistory loginAppHistory = new LoginAppHistory();
        loginAppHistory.setId(loginAppHistoryId);
        loginAppHistory = loginAppHistoryService.get(loginAppHistoryId);
        DetailResponse<LoginAppHistory> success = ResponseUtil.detailSuccess(loginAppHistory);
        return success;
    }


    /**
     * 删除操作
     */
    @ApiOperation("删除")
    @PostMapping("/delete/{loginAppHistoryId}")
    @AnonymousAccess
    public CommonResponse delete(@PathVariable Long loginAppHistoryId) {
        LoginAppHistory loginAppHistory = new LoginAppHistory();
        loginAppHistory.setId(loginAppHistoryId);
        int count = loginAppHistoryService.delete(loginAppHistory);
        CommonResponse success = ResponseUtil.success();
        return success;
    }


    /**
     * 删除操作-批量
     */
    @ApiOperation("批量删除")
    @PostMapping("/batch/delete")
    @AnonymousAccess
    public CommonResponse batchDelete(@RequestBody BatchDTO batchDTO) {
// 获取当前操作人信息
        LoginAppHistoryQueryDTO queryDTO = new LoginAppHistoryQueryDTO();

        queryDTO.setBatchIdList(batchDTO.getBatchIdList());
        int count = loginAppHistoryService.batchDelete(queryDTO);
        CommonResponse success = ResponseUtil.success();
        return success;
    }
}