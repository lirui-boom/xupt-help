package cn.edu.xupt.help.message.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.*;
import cn.edu.xupt.help.message.mapper.TbMessageImagesMapper;
import cn.edu.xupt.help.message.mapper.TbMessageMapper;
import cn.edu.xupt.help.message.mapper.TbUserMapper;
import cn.edu.xupt.help.message.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private TbMessageMapper tbMessageMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbMessageImagesMapper tbMessageImagesMapper;


    private List<TbMessageImages> findImageListByMessageId(Long id) {

        if (id == null) {
            return null;
        }

        TbMessageImagesExample example = new TbMessageImagesExample();
        TbMessageImagesExample.Criteria criteria = example.createCriteria();
        criteria.andMessageIdEqualTo(id);
        return tbMessageImagesMapper.selectByExample(example);
    }

    @Override
    public TbMessage findOneById(Long id) {

        TbMessage tbMessage = tbMessageMapper.selectByPrimaryKey(id);

        if (tbMessage == null) {
            return null;
        }

        List<TbMessageImages> imagesList = findImageListByMessageId(id);

        tbMessage.setImagesList(imagesList);

        return tbMessage;
    }

    @Override
    public PageResult search(int pageNum, int pageSize, TbMessage tbMessage) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbMessageExample example = new TbMessageExample();
        TbMessageExample.Criteria criteria = example.createCriteria();

        if (tbMessage == null) {
            tbMessage = new TbMessage();
        }

        if (tbMessage.getId() != null) {
            criteria.andIdEqualTo(tbMessage.getId());
        }

        if (tbMessage.getCategoryId() != null) {
            criteria.andCategoryIdEqualTo(tbMessage.getCategoryId());
        }

        if (!StringUtils.isEmpty(tbMessage.getIsTop())) {
            criteria.andIsTopEqualTo(tbMessage.getIsTop());
        }

        if (tbMessage.getUserId() != null) {
            criteria.andUserIdEqualTo(tbMessage.getUserId());
        }

        if (!StringUtils.isEmpty(tbMessage.getUsername())) {
            criteria.andUsernameLike("%" + tbMessage.getUsername() + "%");
        }

        if (!StringUtils.isEmpty(tbMessage.getTitle())) {
            criteria.andTitleLike("%" + tbMessage.getTitle() + "%");
        }

        List<TbMessage> tbMessageList = tbMessageMapper.selectByExample(example);

        for (TbMessage message : tbMessageList) {
            message.setImagesList(findImageListByMessageId(message.getId()));
        }

        Page page = (Page) tbMessageList;

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbMessage tbMessage) {

        if (tbMessage == null || StringUtils.isEmpty(tbMessage.getTitle()) ||
                tbMessage.getCategoryId() == null || StringUtils.isEmpty(tbMessage.getContent())){
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (tbMessage.getUserId() == null && StringUtils.isEmpty(tbMessage.getUsername()) ||
                tbMessage.getUserId() != null && !StringUtils.isEmpty(tbMessage.getUsername())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbUser tbUser;

        if (tbMessage.getUserId() != null) {

            tbUser = tbUserMapper.selectByPrimaryKey(tbMessage.getUserId());

            if (tbUser == null) {
                ExceptionCast.cast(ResponseResult.build(400, "该用户不存在！"));
            }

        } else { //tbMessage.getUsername!=null

            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(tbMessage.getUsername());
            criteria.andStatusEqualTo("1");
            List<TbUser> users = tbUserMapper.selectByExample(example);

            if (users == null || users.size() == 0) {
                ExceptionCast.cast(ResponseResult.build(400, "该用户不存在！"));
            }

            tbUser = users.get(0);
        }

        tbMessage.setUserId(tbUser.getId());
        tbMessage.setUsername(tbMessage.getUsername());
        tbMessage.setUserNickName(tbUser.getNickName());
        tbMessage.setUserPic(tbUser.getUserPic());
        tbMessage.setCreateTime(new Date());
        tbMessage.setUpdateTime(new Date());
        tbMessage.setCollectCount(0L);
        tbMessage.setWatchCount(0L);
        tbMessage.setCommentCount(0L);
        tbMessage.setId(null);
        tbMessage.setIsTop("0");

        tbMessageMapper.insert(tbMessage);

        List<TbMessageImages> imagesList = tbMessage.getImagesList();

        if (imagesList != null && imagesList.size() > 0) {

            for (TbMessageImages images : imagesList) {

                images.setId(null);
                tbMessageImagesMapper.insert(images);
            }
        }
    }

    @Override
    public void update(TbMessage tbMessage) {

        if (tbMessage == null || StringUtils.isEmpty(tbMessage.getTitle()) || tbMessage.getId()==null ||
                tbMessage.getCategoryId() == null || StringUtils.isEmpty(tbMessage.getContent())){
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbMessage oldMessage = tbMessageMapper.selectByPrimaryKey(tbMessage.getId());

        tbMessage.setCommentCount(oldMessage.getCommentCount());
        tbMessage.setWatchCount(oldMessage.getWatchCount());
        tbMessage.setIsTop(oldMessage.getIsTop());
        tbMessage.setUserId(oldMessage.getUserId());
        tbMessage.setUsername(oldMessage.getUsername());
        tbMessage.setUserNickName(oldMessage.getUserNickName());
        tbMessage.setUserPic(oldMessage.getUserPic());
        tbMessage.setUpdateTime(new Date());
        tbMessage.setCollectCount(oldMessage.getCollectCount());
        tbMessage.setStatus(oldMessage.getStatus());

        tbMessageMapper.updateByPrimaryKey(tbMessage);

        List<TbMessageImages> imagesList = tbMessage.getImagesList();

        if (imagesList != null && imagesList.size() > 0) {

            for (TbMessageImages images : imagesList) {

                images.setId(null);
                tbMessageImagesMapper.insert(images);
            }
        }
    }

    @Override
    public void updateStatus(Long id, String status) {

        if (id == null || !"0".equals(status) && !"1".equals(status)) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        TbMessage tbMessage = tbMessageMapper.selectByPrimaryKey(id);

        if (tbMessage == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该消息不存在！"));
        }

        tbMessage.setStatus(status);

        tbMessageMapper.updateByPrimaryKey(tbMessage);
    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbMessageMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
