package ${packageName}.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import java.time.LocalDateTime;
import java.time.LocalDate;
import io.swagger.annotations.*;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import com.rapidboot.common.persistence.DataEntity;

<#if isNotNullColumns??>
    import javax.validation.constraints.*;
</#if>
<#if hasDateAnnotation>
    import javax.persistence.Entity;
    import javax.persistence.Table;
    import org.hibernate.annotations.*;
</#if>
<#if hasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
    import java.math.BigDecimal;
</#if>
import java.io.Serializable;

/**
* 既是实体对象，又是数据传输对象（接收页面请求参数），同时又是值对象（为页面提供渲染所需数据）
* 基于lombok ，所以无需生成getter 和 setter方法
* @description /
* @author ${author}
* @date ${date}
**/
@Data
@ApiModel(value = "${className}", description="${apiAlias}实体对象")
<#if jpaEnable??>
    @Entity
    @Table(name="${tableName}")
</#if>
public class ${className} extends DataEntity<${className}>{
<#if columns??>
    <#list columns as column>

        <#if column.columnKey = 'PRI'>
            @Id
            <#if auto>
                @GeneratedValue(strategy = GenerationType.IDENTITY)
            </#if>
        </#if>
        <#if jpaEnable??>
            @Column(name = "${column.columnName}"<#if column.columnKey = 'UNI'>,unique = true</#if><#if column.notNull && column.columnKey != 'PRI'>,nullable = false</#if>)
        </#if>
        <#if column.notNull && column.columnKey != 'PRI'>
            <#if column.columnType = 'String'>
                @NotBlank
            <#else>
                @NotNull
            </#if>
        </#if>
        <#if (column.dateAnnotation)??>
            <#if column.dateAnnotation = 'CreationTimestamp'>
                @CreationTimestamp
            <#else>
                @UpdateTimestamp
            </#if>
        </#if>
        <#if swaggerEnable??>
            <#if column.remark != ''>
                @ApiModelProperty(value = "${column.remark}")
            <#else>
                @ApiModelProperty(value = "${column.changeColumnName}")
            </#if>
        </#if>
        private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

public void copy(${className} source){
BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
}
}