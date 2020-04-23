package cn.edu.xupt.help.comment.service.impl;

import cn.edu.xupt.help.comment.mapper.*;
import cn.edu.xupt.help.comment.service.CommentReplyService;
import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    private TbCommentReplyMapper tbCommentReplyMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbCommentMapper tbCommentMapper;

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbMessageMapper tbMessageMapper;

    @Override
    public TbCommentReply findOneById(Long id) {
        return tbCommentReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbCommentReply> findByCommentId(Long commentId) {

        TbCommentReplyExample example = new TbCommentReplyExample();
        TbCommentReplyExample.Criteria criteria = example.createCriteria();
        criteria.andCommentIdEqualTo(commentId);
        return tbCommentReplyMapper.selectByExample(example);
    }


    @Override
    public void add(TbCommentReply tbCommentReply) {

        if (tbCommentReply == null || tbCommentReply.getCommentId() == null || tbCommentReply.getFromUid() == null ||
                tbCommentReply.getToUid() == null || StringUtils.isEmpty(tbCommentReply.getContent()) || StringUtils.isEmpty(tbCommentReply.getReplyType())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        //回复评论  replyId 置为null
        if ("1".equals(tbCommentReply.getReplyType())) {
            tbCommentReply.setReplyId(null);
        }

        //回复的回复 replyId为空不合法
        if ("2".equals(tbCommentReply.getReplyType()) && tbCommentReply.getReplyId() == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }


        //合法性检查

        //回复人
        TbUser fromUser = tbUserMapper.selectByPrimaryKey(tbCommentReply.getFromUid());

        //被回复人
        TbUser toUser = tbUserMapper.selectByPrimaryKey(tbCommentReply.getToUid());

        if (fromUser == null || toUser == null) {
            ExceptionCast.cast(ResponseResult.build(400, "回复人或被回复人不存在！"));
        }

        TbComment tbComment = new TbComment();

        if ("1".equals(tbCommentReply.getReplyType())) { //回复评论

             tbComment = tbCommentMapper.selectByPrimaryKey(tbCommentReply.getCommentId());

            if (tbComment == null) {
                ExceptionCast.cast(ResponseResult.build(400, "评论信息不存在！"));
            }
        }

        if ("2".equals(tbCommentReply.getReplyType())) { //回复的回复

            TbCommentReply parentReply = tbCommentReplyMapper.selectByPrimaryKey(tbCommentReply.getReplyId());
            tbComment = tbCommentMapper.selectByPrimaryKey(tbCommentReply.getCommentId());

            if (parentReply == null) {
                ExceptionCast.cast(ResponseResult.build(400, "回复信息不存在！"));
            }
        }

        tbCommentReply.setCreateTime(new Date());
        tbCommentReply.setFromNickName(fromUser.getNickName());
        tbCommentReply.setFromThumbImg(fromUser.getUserPic());
        tbCommentReply.setToNickName(toUser.getNickName());
        tbCommentReply.setToThumbImg(toUser.getUserPic());

        tbCommentReplyMapper.insert(tbCommentReply);

        //更新评论数

        if (tbComment.getTopicType().intValue() == 1) { //message
            updateMessageCommentCount(tbComment.getTopicId());
        }

        if (tbComment.getTopicType().intValue() == 2) { //goods
            updateGoodsCommentCount(tbComment.getTopicId());
        }

    }

    //更新商品信息的评论数
    private void updateGoodsCommentCount(Long goodsId) {

        if (goodsId == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(goodsId);

        if (tbGoods == null) {
            ExceptionCast.cast(ResponseResult.build(400,"该商品信息不存在！"));
        }

        Long count = 0L;

        TbCommentExample commentExample = new TbCommentExample();
        TbCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andTopicTypeEqualTo(2);
        commentExampleCriteria.andTopicIdEqualTo(goodsId);
        commentExampleCriteria.andStatusEqualTo("1");

        List<TbComment> tbComments = tbCommentMapper.selectByExample(commentExample);
        count += tbComments.size();

        for (TbComment tbComment : tbComments) {

            TbCommentReplyExample commentReplyExample = new TbCommentReplyExample();
            TbCommentReplyExample.Criteria replyExampleCriteria = commentReplyExample.createCriteria();
            replyExampleCriteria.andCommentIdEqualTo(tbComment.getId());
            List<TbCommentReply> replies = tbCommentReplyMapper.selectByExample(commentReplyExample);
            //更新评论的回复数
            tbComment.setReplyNum(replies.size());
            tbCommentMapper.updateByPrimaryKey(tbComment);
            count += replies.size();
        }

        tbGoods.setCommentCount(count);
        tbGoodsMapper.updateByPrimaryKey(tbGoods);
    }

    //更新消息信息的评论数
    private void updateMessageCommentCount(Long messageId) {

        if (messageId == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbMessage tbMessage = tbMessageMapper.selectByPrimaryKey(messageId);

        if (tbMessage == null) {
            ExceptionCast.cast(ResponseResult.build(400,"该消息信息不存在！"));
        }

        Long count = 0L;

        TbCommentExample commentExample = new TbCommentExample();
        TbCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andTopicTypeEqualTo(1);
        commentExampleCriteria.andTopicIdEqualTo(messageId);
        commentExampleCriteria.andStatusEqualTo("1");

        List<TbComment> tbComments = tbCommentMapper.selectByExample(commentExample);
        count += tbComments.size();

        for (TbComment tbComment : tbComments) {

            TbCommentReplyExample commentReplyExample = new TbCommentReplyExample();
            TbCommentReplyExample.Criteria replyExampleCriteria = commentReplyExample.createCriteria();
            replyExampleCriteria.andCommentIdEqualTo(tbComment.getId());
            List<TbCommentReply> replies = tbCommentReplyMapper.selectByExample(commentReplyExample);
            //更新评论的回复数
            tbComment.setReplyNum(replies.size());
            tbCommentMapper.updateByPrimaryKey(tbComment);
            count += replies.size();
        }

        tbMessage.setCommentCount(count);
        tbMessageMapper.updateByPrimaryKey(tbMessage);
    }

    @Override
    public void updateContent(Long id,String content) {

        if (id == null || StringUtils.isEmpty(content)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbCommentReply tbCommentReply = findOneById(id);
        tbCommentReply.setContent(content);
        tbCommentReplyMapper.updateByPrimaryKey(tbCommentReply);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbCommentReply tbCommentReply = findOneById(id);

        if (tbCommentReply == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该回复信息不存在！"));
        }

        tbCommentReplyMapper.deleteByPrimaryKey(id);

        TbComment tbComment = tbCommentMapper.selectByPrimaryKey(tbCommentReply.getCommentId());

        //更新评论数

        if (tbComment.getTopicType().intValue() == 1) { //message
            updateMessageCommentCount(tbComment.getTopicId());
        }

        if (tbComment.getTopicType().intValue() == 2) { //goods
            updateGoodsCommentCount(tbComment.getTopicId());
        }

    }
}
