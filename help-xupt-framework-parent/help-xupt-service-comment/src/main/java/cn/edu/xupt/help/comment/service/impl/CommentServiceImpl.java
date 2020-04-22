package cn.edu.xupt.help.comment.service.impl;

import cn.edu.xupt.help.comment.mapper.*;
import cn.edu.xupt.help.comment.service.CommentService;
import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TbCommentMapper tbCommentMapper;

    @Autowired
    private TbCommentReplyMapper tbCommentReplyMapper;

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbMessageMapper tbMessageMapper;

    @Override
    public TbComment findOneById(Long id) {
        return tbCommentMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Map<String, List<TbCommentReply>>> findAllCommentByTopicTypeAndTopicId(Integer topicType, Long topicId) {

        List<Map<String, List<TbCommentReply>>> resultList = new ArrayList<Map<String, List<TbCommentReply>>>();

        TbCommentExample example = new TbCommentExample();
        TbCommentExample.Criteria criteria = example.createCriteria();
        criteria.andTopicTypeEqualTo(topicType);
        criteria.andTopicIdEqualTo(topicId);
        criteria.andStatusEqualTo("1");

        List<TbComment> tbComments = tbCommentMapper.selectByExample(example);

        //查询回复
        for (TbComment tbComment : tbComments) {

            Map<String, List<TbCommentReply>> commentEntity = new HashMap<String, List<TbCommentReply>>();

            TbCommentReplyExample replyExample = new TbCommentReplyExample();
            TbCommentReplyExample.Criteria replyExampleCriteria = replyExample.createCriteria();
            replyExampleCriteria.andCommentIdEqualTo(tbComment.getId());
            List<TbCommentReply> tbReplies = tbCommentReplyMapper.selectByExample(replyExample);

            commentEntity.put(JSON.toJSONString(tbComment), tbReplies);

            resultList.add(commentEntity);
        }

        return resultList;
    }

    @Override
    public void updateStatus(Long id, String status) {

        if (id == null || !"0".equals(status) && !"1".equals(status) && !"2".equals(status)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbComment tbComment = findOneById(id);

        if (tbComment == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该评论不存在！"));
        }

        if (tbComment.getStatus().equals(status)) {
            return;
        }

        tbComment.setStatus(status);
        tbCommentMapper.updateByPrimaryKey(tbComment);

        //更新商品信息的评论数
        if (tbComment.getTopicType().intValue() == 1 ) { //消息评论
            updateMessageCommentCount(tbComment.getTopicId());
        }

        if (tbComment.getTopicType().intValue() == 2 ) { //商品评论
            updateGoodsCommentCount(tbComment.getTopicId());
        }
    }

    @Override
    public PageResult search(int pageNum, int pageSize, TbComment tbComment) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbCommentExample example = new TbCommentExample();
        TbCommentExample.Criteria criteria = example.createCriteria();

        if (tbComment.getFromUid() != null) {
            criteria.andFromUidEqualTo(tbComment.getFromUid());
        }

        if (tbComment.getIsHot() != null) {
            criteria.andIsHotEqualTo(tbComment.getIsHot());
        }

        if (tbComment.getIsTop() != null) {
            criteria.andIsTopEqualTo(tbComment.getIsTop());
        }

        if (tbComment.getTopicId() != null) {
            criteria.andTopicIdEqualTo(tbComment.getTopicId());
        }

        if (tbComment.getTopicType() != null) {
            criteria.andTopicTypeEqualTo(tbComment.getTopicType());
        }

        if (tbComment.getStatus() != null) {
            criteria.andStatusEqualTo(tbComment.getStatus());
        }

        Page page = (Page) tbCommentMapper.selectByExample(example);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbComment tbComment) {

        if (tbComment == null || tbComment.getTopicType() == null || tbComment.getTopicId() == null
                || tbComment.getFromUid() == null || StringUtils.isEmpty(tbComment.getContent())) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        TbUser tbUser = tbUserMapper.selectByPrimaryKey(tbComment.getFromUid());

        if (tbUser == null) {
            ExceptionCast.cast(ResponseResult.build(400,"该用户不存在！"));
        }

        if (tbComment.getTopicType().intValue() == 1) { //消息评论
            TbMessage tbMessage = tbMessageMapper.selectByPrimaryKey(tbComment.getTopicId());

            if (tbMessage == null) {
                ExceptionCast.cast(ResponseResult.build(400,"该消息不存在！"));
            }
        }


        if (tbComment.getTopicType().intValue() == 2) { //商品评论
            TbGoods goods = tbGoodsMapper.selectByPrimaryKey(tbComment.getTopicId());

            if (goods == null) {
                ExceptionCast.cast(ResponseResult.build(400,"该商品信息不存在！"));
            }
        }

        tbComment.setNickName(tbUser.getNickName());
        tbComment.setThumbImg(tbUser.getUserPic());
        tbComment.setCreateTime(new Date());
        tbComment.setStatus("1");
        tbComment.setIsHot("0");
        tbComment.setIsTop("0");
        tbComment.setLikeNum(0);
        tbComment.setReplyNum(0);

        tbCommentMapper.insert(tbComment);

        //更新商品信息的评论数
        if (tbComment.getTopicType().intValue() == 1 ) { //消息评论
            updateMessageCommentCount(tbComment.getTopicId());
        }

        if (tbComment.getTopicType().intValue() == 2 ) { //商品评论
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
    public void updateContent(Long id, String content) {

        if (id == null || StringUtils.isEmpty(content)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }


        TbComment tbComment = findOneById(id);

        if (tbComment == null ) {
            ExceptionCast.cast(ResponseResult.build(400, "该评论不存在！"));
        }

        tbComment.setContent(content);

        tbCommentMapper.updateByPrimaryKey(tbComment);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbComment tbComment = findOneById(id);

        if (tbComment == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该评论不存在！"));
        }

        tbCommentMapper.deleteByPrimaryKey(id);

        //更新商品信息的评论数
        if (tbComment.getTopicType().intValue() == 1 ) { //消息评论
            updateMessageCommentCount(tbComment.getTopicId());
        }

        if (tbComment.getTopicType().intValue() == 2 ) { //商品评论
            updateGoodsCommentCount(tbComment.getTopicId());
        }

    }
}
