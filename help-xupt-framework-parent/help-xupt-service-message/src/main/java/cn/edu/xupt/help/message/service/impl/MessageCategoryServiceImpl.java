package cn.edu.xupt.help.message.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbMessageCategory;
import cn.edu.xupt.help.message.mapper.TbMessageCategoryMapper;
import cn.edu.xupt.help.message.service.MessageCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageCategoryServiceImpl implements MessageCategoryService {

    @Autowired
    private TbMessageCategoryMapper tbMessageCategoryMapper;

    @Override
    public List<TbMessageCategory> findAll() {
        return tbMessageCategoryMapper.selectByExample(null);
    }

    @Override
    public TbMessageCategory findOneById(Long id) {
        return tbMessageCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(TbMessageCategory tbMessageCategory) {

        if (tbMessageCategory == null || StringUtils.isEmpty(tbMessageCategory.getCategoryName())) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        tbMessageCategory.setId(null);
        tbMessageCategoryMapper.insert(tbMessageCategory);
    }

    @Override
    public void update(TbMessageCategory tbMessageCategory) {

        if (tbMessageCategory == null || StringUtils.isEmpty(tbMessageCategory.getCategoryName()) || tbMessageCategory.getId() == null) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        tbMessageCategoryMapper.updateByPrimaryKey(tbMessageCategory);
    }


    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbMessageCategoryMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
