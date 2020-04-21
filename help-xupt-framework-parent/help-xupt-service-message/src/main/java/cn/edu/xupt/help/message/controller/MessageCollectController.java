package cn.edu.xupt.help.message.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbMessageCollect;
import cn.edu.xupt.help.message.service.MessageCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"消息收藏接口"})
@RestController
@RequestMapping("/message/collect")
public class MessageCollectController {

    @Autowired
    private MessageCollectService messageCollectService;

    @ApiOperation("根据id查询消息收藏")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(messageCollectService.findOneById(id));
    }

    @ApiOperation("根据userid查询消息收藏")
    @GetMapping("findMessageCollectByUserId")
    public  ResponseResult findMessageCollectByUserId(Long userId) {
        return ResponseResult.ok(messageCollectService.findMessageCollectByUserId(userId));
    }

    @ApiOperation("添加收藏")
    @PostMapping("add")
    public  ResponseResult add(@RequestBody TbMessageCollect tbMessageCollect) {
        messageCollectService.add(tbMessageCollect);
        return ResponseResult.ok();
    }

    @ApiOperation("根据ids删除收藏")
    @DeleteMapping("deleteIds")
    public  ResponseResult deleteIds(Long[] ids) {
        messageCollectService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
