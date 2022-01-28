package ${packageName}.controller;


import com.rapidboot.common.annotation.AnonymousAccess;
import com.rapidboot.common.persistence.BatchDTO;
import com.rapidboot.common.result.*;
import org.springframework.beans.factory.annotation.Autowired;



import com.github.pagehelper.PageInfo;
import ${packageName}.domain.${className};
import ${packageName}.service.${className}Service;
import ${packageName}.dto.${className}QueryDTO;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author ${author}
* @date ${date}
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "${apiAlias}管理")
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

@Autowired
private ${className}Service ${changeClassName}Service;

/**
* 列表查询
*/
@ApiOperation(value = " 列表查询")
@GetMapping("list")
@AnonymousAccess
public PageResponse<${className}> list(${className}QueryDTO queryDTO) {
PageInfo<${className}> pageInfo = ${changeClassName}Service.findPage(queryDTO);
PageResponse<${className}> pageResponse = ResponseUtil.pageSuccess(pageInfo);
return pageResponse;
}

/**
* 保存或修改
*/
@ApiOperation(value = " 保存或修改")
@PostMapping("/save")
@AnonymousAccess
public DetailResponse<${className}> save(@RequestBody ${className} ${changeClassName}) {
${changeClassName}Service.save(${changeClassName});
DetailResponse<${className}> success = ResponseUtil.detailSuccess(${changeClassName});
return success;
}


/**
* 详情
*/
@ApiOperation("详情")
@GetMapping("/detail/{${changeClassName}Id}")
@AnonymousAccess
public DetailResponse<${className}> detail(@PathVariable Long ${changeClassName}Id) {
${className}  ${changeClassName}=new ${className}();
${changeClassName}.setId(${changeClassName}Id);
${changeClassName} = ${changeClassName}Service.get(${changeClassName}Id);
DetailResponse<${className}> success = ResponseUtil.detailSuccess(${changeClassName});
return success;
}


/**
* 删除操作
*/
@ApiOperation("删除")
@PostMapping("/delete/{${changeClassName}Id}")
@AnonymousAccess
public CommonResponse delete(@PathVariable Long ${changeClassName}Id) {
${className}    ${changeClassName}=new ${className}();
${changeClassName}.setId(${changeClassName}Id);
int count=${changeClassName}Service.delete(${changeClassName});
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
${className}QueryDTO    queryDTO=new ${className}QueryDTO();

queryDTO.setBatchIdList(batchDTO.getBatchIdList());
int count=${changeClassName}Service.batchDelete(queryDTO);
CommonResponse success = ResponseUtil.success();
return success;
}
}