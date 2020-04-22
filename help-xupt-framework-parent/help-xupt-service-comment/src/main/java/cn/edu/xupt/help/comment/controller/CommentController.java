package cn.edu.xupt.help.comment.controller;

import cn.edu.xupt.help.comment.service.CommentService;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"评论接口"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("根据id查询评论信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(commentService.findOneById(id));
    }

    @ApiOperation("根据主题类型和主题id查询所有评论信息")
    @GetMapping("findAllCommentByTopicTypeAndTopicId")
    public  ResponseResult findAllCommentByTopicTypeAndTopicId(Integer topicType, Long topicId) {
        return ResponseResult.ok(commentService.findAllCommentByTopicTypeAndTopicId(topicType,topicId));
    }

    @ApiOperation("修改评论信息状态")
    @PutMapping("updateStatus")
    public  ResponseResult updateStatus(Long id, String status) {
        commentService.updateStatus(id, status);
        return ResponseResult.ok();
    }

    @ApiOperation("评论条件分页查询")
    @PostMapping("search")
    public  ResponseResult search(int pageNum, int pageSize,@RequestBody TbComment tbComment) {
        return ResponseResult.ok(commentService.search(pageNum,pageSize,tbComment));
    }

    @ApiOperation("新增评论")
    @PostMapping("add")
    public  ResponseResult add(@RequestBody TbComment tbComment) {
        commentService.add(tbComment);
        return ResponseResult.ok();
    }

    @ApiOperation("修改评论内容")
    @PutMapping("updateContent")
    public  ResponseResult updateContent(Long id, String content) {
        commentService.updateContent(id,content);
        return ResponseResult.ok();
    }

    @ApiOperation("删除评论")
    @DeleteMapping("deleteById")
    public  ResponseResult deleteById(Long id) {
        commentService.deleteById(id);
        return ResponseResult.ok();
    }
}
