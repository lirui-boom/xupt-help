package cn.edu.xupt.help.comment.service;

import cn.edu.xupt.help.framework.domain.TbCommentReply;

import java.util.List;

public interface CommentReplyService {

    public abstract TbCommentReply findOneById(Long id);

    public abstract List<TbCommentReply> findByCommentId(Long commentId);

    public abstract void add(TbCommentReply tbCommentReply);

    public abstract void updateContent(Long id, String content);

    public abstract void deleteById(Long id);
}
