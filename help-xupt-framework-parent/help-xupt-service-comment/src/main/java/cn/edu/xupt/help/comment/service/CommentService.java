package cn.edu.xupt.help.comment.service;

import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.domain.TbComment;
import cn.edu.xupt.help.framework.domain.TbCommentReply;

import java.util.List;
import java.util.Map;

public interface CommentService {

    public abstract TbComment findOneById(Long id);

    public abstract List<Map<String, List<TbCommentReply>>> findAllCommentByTopicTypeAndTopicId(Integer topicType, Long topicId);

    public abstract void updateStatus(Long id, String status);

    public abstract PageResult search(int pageNum, int pageSize, TbComment tbComment);

    public abstract void add(TbComment tbComment);

    public abstract void updateContent(Long id, String content);

    public abstract void deleteById(Long id);

}
