package cn.edu.xupt.help.link.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbLink;
import cn.edu.xupt.help.link.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"联系接口"})
@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @ApiOperation("根据id查询联系信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(linkService.findOneById(id));
    }

    @ApiOperation("查询两个用户之间所有联系信息")
    @GetMapping("findAllLink")
    public ResponseResult findAllLink(Long userId1, Long userId2) {
        return ResponseResult.ok(linkService.findAllLink(userId1, userId2));
    }

    @ApiOperation("新增联系信息")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbLink tbLink) {
        linkService.add(tbLink);
        return ResponseResult.ok();
    }

    @ApiOperation("删除联系信息")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        linkService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
