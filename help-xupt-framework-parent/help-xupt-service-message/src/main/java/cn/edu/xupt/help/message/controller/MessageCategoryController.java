package cn.edu.xupt.help.message.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbMessageCategory;
import cn.edu.xupt.help.message.service.MessageCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"消息分类接口"})
@RestController
@RequestMapping("/message/category")
public class MessageCategoryController {

    @Autowired
    private MessageCategoryService messageCategoryService;

    @ApiOperation("查询所有消息分类")
    @GetMapping("findAll")
    public ResponseResult findAll() {
        return ResponseResult.ok(messageCategoryService.findAll());
    }

    @ApiOperation("根据id查询消息分类信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(messageCategoryService.findOneById(id));
    }

    @ApiOperation("新增消息分类信息")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbMessageCategory tbMessageCategory) {
        messageCategoryService.add(tbMessageCategory);
        return ResponseResult.ok();
    }

    @ApiOperation("修改消息分类信息")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbMessageCategory tbMessageCategory) {
        messageCategoryService.update(tbMessageCategory);
        return ResponseResult.ok();
    }

    @ApiOperation("根据ids数组删除消息分类")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        messageCategoryService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
