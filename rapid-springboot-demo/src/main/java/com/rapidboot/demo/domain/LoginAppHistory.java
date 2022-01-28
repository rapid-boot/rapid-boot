package com.rapidboot.demo.domain;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.time.LocalDate;
import io.swagger.annotations.*;
import javax.persistence.*;
import com.rapidboot.common.persistence.DataEntity;

    import javax.validation.constraints.*;
import java.io.Serializable;

/**
* 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
* 基于lombok ，所以无需生成getter 和 setter方法
* @description /
* @author 广峰 <gf@gfzj.us>
* @date 2022-01-29 02:41
**/
@Data
@ApiModel(value = "LoginAppHistory", description="这个是很牛逼的接口，其实直接引用 table 的介绍就行了,先偷懒实体对象")
public class LoginAppHistory extends DataEntity<LoginAppHistory>{

            @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

                @NotNull
        private Long loginId;

                @NotBlank
        private String acction;

        private Integer role;

        private String loginIp;

                @NotNull
        private LocalDateTime loginTime;

                @NotNull
        private Integer loginMethod;

                @NotNull
        private Integer loginType;

        private String token;

        private String uuid;

        private String appVersion;

        private String vendor;

        private String model;

        private String sysVersion;

public void copy(LoginAppHistory source){
    BeanUtils.copyProperties(source, this);
}
}