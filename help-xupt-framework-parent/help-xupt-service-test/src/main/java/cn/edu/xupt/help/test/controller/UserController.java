package cn.edu.xupt.help.test.controller;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"用户接口"})
@RestController
@RequestMapping("test")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 试例1 查询数据库
     */
    @ApiOperation("试例1 查询数据库")
    @GetMapping("findAll")
    public ResponseResult findAll() {
        return ResponseResult.ok(userService.findAll());
    }

    /**
     * 试例2 异常处理
     */
    @ApiOperation("试例2 异常处理")
    @GetMapping("error")
    public ResponseResult error() {
        ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        return null;
    }
}
