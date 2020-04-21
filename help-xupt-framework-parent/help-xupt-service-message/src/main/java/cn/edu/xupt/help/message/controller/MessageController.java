package cn.edu.xupt.help.message.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbMessage;
import cn.edu.xupt.help.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"消息接口"})
@RestController
@RequestMapping("message")
public class MessageController {


    @Autowired
    private MessageService messageService;

    @ApiOperation("根据id查询消息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(messageService.findOneById(id));
    }

    @ApiOperation("消息分页条件查询")
    @PostMapping("search")
    public  ResponseResult search(int pageNum, int pageSize,@RequestBody TbMessage tbMessage) {
        return ResponseResult.ok(messageService.search(pageNum, pageSize, tbMessage));
    }

    @ApiOperation("添加消息")
    @PostMapping("add")
    public  ResponseResult add(@RequestBody TbMessage tbMessage) {
        messageService.add(tbMessage);
        return ResponseResult.ok();
    }

    @ApiOperation("修改消息内容")
    @PutMapping("update")
    public  ResponseResult update(@RequestBody TbMessage tbMessage) {
        messageService.update(tbMessage);
        return ResponseResult.ok();
    }

    @ApiOperation("修改消息状态")
    @PutMapping("updateStatus")
    public  ResponseResult updateStatus(Long id, String status) {
        messageService.updateStatus(id,status);
        return ResponseResult.ok();
    }

    @ApiOperation("根据ids数组删除消息")
    @DeleteMapping("deleteIds")
    public  ResponseResult deleteIds(Long[] ids) {
        messageService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
