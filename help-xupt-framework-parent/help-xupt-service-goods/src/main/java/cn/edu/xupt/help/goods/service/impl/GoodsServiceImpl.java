package cn.edu.xupt.help.goods.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.*;
import cn.edu.xupt.help.goods.mapper.TbCommentMapper;
import cn.edu.xupt.help.goods.mapper.TbGoodsMapper;
import cn.edu.xupt.help.goods.mapper.TbUserMapper;
import cn.edu.xupt.help.goods.service.GoodsService;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbCommentMapper tbCommentMapper;

    @Override
    public TbGoods findOneById(Long id) {
        return tbGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult search(int pageNum, int pageSize, TbGoods tbGoods) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbGoodsExample example = new TbGoodsExample();
        TbGoodsExample.Criteria criteria = example.createCriteria();

        if (tbGoods.getId() != null) {
            criteria.andIdEqualTo(tbGoods.getId());
        }

        if (tbGoods.getCategoryId() != null) {
            criteria.andCategoryIdEqualTo(tbGoods.getCategoryId());
        }

        if (!StringUtils.isEmpty(tbGoods.getUsername())) {
            criteria.andUsernameLike("%" + tbGoods.getUsername() + "%");
        }

        if (!StringUtils.isEmpty(tbGoods.getGoodsName())) {
            criteria.andGoodsNameLike("%" + tbGoods.getGoodsName() + "%");
        }

        if (!StringUtils.isEmpty(tbGoods.getIsTop())) {
            criteria.andIsTopEqualTo(tbGoods.getIsTop());
        }

        if (!StringUtils.isEmpty(tbGoods.getStatus())) {
            criteria.andStatusEqualTo(tbGoods.getStatus());
        }

        if (tbGoods.getUserId() != null) {
            criteria.andUserIdEqualTo(tbGoods.getUserId());
        }

        Page page = (Page) tbGoodsMapper.selectByExample(example);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbGoods tbGoods) {

        if (tbGoods == null || StringUtils.isEmpty(tbGoods.getGoodsName()) || tbGoods.getCategoryId() == null
                || tbGoods.getPrice() == null || StringUtils.isEmpty(tbGoods.getContent())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (tbGoods.getUserId() == null && StringUtils.isEmpty(tbGoods.getUsername()) ||
                tbGoods.getUserId() != null && !StringUtils.isEmpty(tbGoods.getUsername())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbUser tbUser;

        if (tbGoods.getUserId() != null) {

            tbUser = tbUserMapper.selectByPrimaryKey(tbGoods.getUserId());

            if (tbUser == null) {
                ExceptionCast.cast(ResponseResult.build(400, "该用户不存在！"));
            }

        } else {

            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(tbGoods.getUsername());
            List<TbUser> users = tbUserMapper.selectByExample(example);

            if (users == null || users.size() == 0) {
                ExceptionCast.cast(ResponseResult.build(400, "该用户不存在！"));
            }

            tbUser = users.get(0);
        }

        if (!tbUser.getStatus().equals("1")) {
            ExceptionCast.cast(ResponseResult.build(400, "该用户账户不可用"));
        }

        tbGoods.setUserId(tbUser.getId());
        tbGoods.setUsername(tbUser.getUsername());
        tbGoods.setUserNickName(tbUser.getNickName());
        tbGoods.setUserPic(tbUser.getUserPic());
        tbGoods.setPraseCount(0L);
        tbGoods.setCommentCount(0L);
        tbGoods.setIsTop("0");
        tbGoods.setStatus("1");
        tbGoods.setCreateTime(new Date());

        tbGoodsMapper.insert(tbGoods);
    }

    @Override
    public void update(TbGoods tbGoods) {

        if (tbGoods == null || tbGoods.getId() == null || StringUtils.isEmpty(tbGoods.getGoodsName()) || tbGoods.getUserId() == null || tbGoods.getCategoryId() == null
                || tbGoods.getPrice() == null || tbGoods.getContent() == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbGoods goods = tbGoodsMapper.selectByPrimaryKey(tbGoods.getId());

        //以下数据不允许随意修改
        tbGoods.setStatus(goods.getStatus());
        tbGoods.setCreateTime(goods.getCreateTime());
        tbGoods.setUserId(tbGoods.getUserId());
        tbGoods.setUsername(tbGoods.getUsername());
        tbGoods.setUserNickName(tbGoods.getUserNickName());
        tbGoods.setUserPic(tbGoods.getUserPic());
        tbGoods.setCommentCount(tbGoods.getCommentCount());
        tbGoods.setIsTop(tbGoods.getIsTop());
        tbGoods.setPraseCount(tbGoods.getPraseCount());

        tbGoodsMapper.updateByPrimaryKey(tbGoods);
    }

    @Override
    public void updateStatus(Long id, String status) {

        if (id == null || StringUtils.isEmpty(status) || !"0".equals(status) && !"1".equals(status)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbGoods goods = tbGoodsMapper.selectByPrimaryKey(id);

        if (goods == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该商品不存在！"));
        }

        goods.setStatus(status);

        tbGoodsMapper.updateByPrimaryKey(goods);

    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {

                tbGoodsMapper.deleteByPrimaryKey(id);
                //下面删除评论
                TbCommentExample example = new TbCommentExample();
                TbCommentExample.Criteria criteria = example.createCriteria();
                criteria.andTopicTypeEqualTo(2);
                criteria.andTopicIdEqualTo(id);
                tbCommentMapper.deleteByExample(example);
            }
        }

    }
}
