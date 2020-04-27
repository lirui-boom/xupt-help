package cn.edu.xupt.help.notice.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbNotice;
import cn.edu.xupt.help.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"公告接口"})
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("查询所有发布公告")
    @GetMapping("findAll")
    public ResponseResult findAll() {
        return ResponseResult.ok(noticeService.findAll());
    }

    @ApiOperation("公告条件分页查询")
    @PostMapping("search")
    public ResponseResult search(int pageNum, int pageSize, @RequestBody TbNotice tbNotice) {
        return ResponseResult.ok(noticeService.search(pageNum,pageSize,tbNotice));
    }

    @ApiOperation("根据id查询公告信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(noticeService.findOneById(id));
    }

    @ApiOperation("新增公告")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbNotice tbNotice) {
        noticeService.add(tbNotice);
        return ResponseResult.ok();
    }

    @ApiOperation("修改公告信息")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbNotice tbNotice) {
        noticeService.update(tbNotice);
        return ResponseResult.ok();
    }

    @ApiOperation("修改公告状态")
    @PutMapping("updateStatusIds")
    public ResponseResult updateStatusIds(Long[] ids, String status) {
        noticeService.updateStatusIds(ids, status);
        return ResponseResult.ok();
    }

    @ApiOperation("删除公告")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        noticeService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
