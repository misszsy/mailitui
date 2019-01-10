package com.zhou.web.controller.api;

import com.zhou.busi.entity.Contact;
import com.zhou.busi.service.ContactService;
import com.zhou.framework.resp.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/contact")
@Api(value = "ApiContactController",description = "预约管理api")
public class ApiContactController {


    @Autowired
    private ContactService contactService;


    /**
     * 新增
     * @param contact
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "新增预约",notes = "添加预约人",httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "预约名称",required = true, paramType = "query", dataType = "String"),
    })
    public @ResponseBody
    R save(@RequestBody Contact contact) {
        if(StringUtils.isEmpty(contact.getMobile())){
           return R.fail("手机号码不能为空");
        }
        if(StringUtils.isEmpty(contact.getName())){
           return R.fail("预约名称不能为空");
        }
        contact.setCreateDate(LocalDateTime.now());
        contactService.save(contact);
        return R.ok();
    }
}
