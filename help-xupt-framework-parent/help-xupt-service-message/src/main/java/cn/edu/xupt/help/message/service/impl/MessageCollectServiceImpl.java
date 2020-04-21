package cn.edu.xupt.help.message.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbMessageCollect;
import cn.edu.xupt.help.framework.domain.TbMessageCollectExample;
import cn.edu.xupt.help.message.mapper.TbMessageCollectMapper;
import cn.edu.xupt.help.message.mapper.TbMessageMapper;
import cn.edu.xupt.help.message.mapper.TbUserMapper;
import cn.edu.xupt.help.message.service.MessageCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageCollectServiceImpl implements MessageCollectService {

    @Autowired
    private TbMessageCollectMapper tbMessageCollectMapper;

    @Autowired
    private TbMessageMapper tbMessageMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbMessageCollect findOneById(Long id) {
        return tbMessageCollectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbMessageCollect> findMessageCollectByUserId(Long userId) {

        if (userId == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (tbUserMapper.selectByPrimaryKey(userId) == null) {
            ExceptionCast.cast(ResponseResult.build(400,"该用户不存在！"));
        }

        TbMessageCollectExample example = new TbMessageCollectExample();
        TbMessageCollectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);

        return tbMessageCollectMapper.selectByExample(example);
    }

    @Override
    public void add(TbMessageCollect tbMessageCollect) {

        if (tbMessageCollect == null || tbMessageCollect.getUserId() == null || tbMessageCollect.getMessageId() == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (tbMessageMapper.selectByPrimaryKey(tbMessageCollect.getMessageId()) == null) {
            ExceptionCast.cast(ResponseResult.build(400,"添加失败，该消息不存在！"));
        }

        if (tbUserMapper.selectByPrimaryKey(tbMessageCollect.getUserId()) == null) {
            ExceptionCast.cast(ResponseResult.build(400,"添加失败，该用户不存在！"));
        }

        tbMessageCollect.setCreateTime(new Date());

        tbMessageCollectMapper.insert(tbMessageCollect);
    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, " 参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbMessageCollectMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
