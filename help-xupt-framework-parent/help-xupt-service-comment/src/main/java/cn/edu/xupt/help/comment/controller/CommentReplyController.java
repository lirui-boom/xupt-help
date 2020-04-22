package cn.edu.xupt.help.comment.controller;

import cn.edu.xupt.help.comment.service.CommentReplyService;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbCommentReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"评论回复接口"})
@RestController
@RequestMapping("/comment/reply")
public class CommentReplyController {

    @Autowired
    private CommentReplyService commentReplyService;

    @ApiOperation("根据id查询回复信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(commentReplyService.findOneById(id));
    }

    @ApiOperation("根据评论id查询该评论下所有的回复")
    @GetMapping("findByCommentId")
    public  ResponseResult findByCommentId(Long commentId) {
        return ResponseResult.ok(commentReplyService.findByCommentId(commentId));
    }

    @ApiOperation("新增回复")
    @PostMapping("add")
    public  ResponseResult add(@RequestBody TbCommentReply tbCommentReply) {
        commentReplyService.add(tbCommentReply);
        return ResponseResult.ok();
    }

    @ApiOperation("修改回复内容")
    @PutMapping("updateContent")
    public  ResponseResult updateContent(Long id, String content) {
        commentReplyService.updateContent(id, content);
        return ResponseResult.ok();
    }

    @ApiOperation("删除回复")
    @DeleteMapping("deleteById")
    public  ResponseResult deleteById(Long id) {
        commentReplyService.deleteById(id);
        return ResponseResult.ok();
    }
}
