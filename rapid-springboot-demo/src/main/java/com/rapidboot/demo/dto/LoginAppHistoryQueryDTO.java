package com.rapidboot.demo.dto;

import com.rapidboot.common.persistence.BaseQueryDTO;
import io.swagger.annotations.*;
import java.time.*;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
/**
* @description Query 对象 用来接收页面传递过来的查询条件参数,并将其传递到 serveice 层，dao 层
* @author 广峰 <gf@gfzj.us>
* @date 2022-01-29 02:17
**/
@ApiModel(value = "LoginAppHistoryQueryDTO", description="这个是很牛逼的接口，其实直接引用 table 的介绍就行了,先偷懒 查询参数对象")
@Data
public class LoginAppHistoryQueryDTO extends BaseQueryDTO  {

private static final long serialVersionUID = 1L;
        @ApiModelProperty(value = "")
        private Long id;
        @ApiModelProperty(value = "登录ID")
        private Long loginId;
        @ApiModelProperty(value = "登录账号")
        private String acction;
        @ApiModelProperty(value = "角色 1-客户 2-车辆")
        private Integer role;
        @ApiModelProperty(value = "登录IP")
        private String loginIp;
        @ApiModelProperty(value = "登录时间")
        private LocalDateTime loginTime;
        @ApiModelProperty(value = "登录方式 1-自动登录 2-账号密码登录")
        private Integer loginMethod;
        @ApiModelProperty(value = "登录介质 1-android 2-IOS 3-PC 4-WAP")
        private Integer loginType;
        @ApiModelProperty(value = "登录token")
        private String token;
        @ApiModelProperty(value = "登录介质的UUID")
        private String uuid;
        @ApiModelProperty(value = "当前软件版本")
        private String appVersion;
        @ApiModelProperty(value = "手机设备厂家")
        private String vendor;
        @ApiModelProperty(value = "手机型号")
        private String model;
        @ApiModelProperty(value = "手机系统版本号")
        private String sysVersion;
        @ApiModelProperty(value = "范围筛选 登录时间开始")
        private LocalDateTime beginLoginTime;
        @ApiModelProperty(value = "范围筛选 登录时间结束")
        private LocalDateTime endLoginTime;
}