package cn.edu.xupt.help.goods.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbGoodsCategory;
import cn.edu.xupt.help.goods.mapper.TbGoodsCategoryMapper;
import cn.edu.xupt.help.goods.service.GoodsCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private TbGoodsCategoryMapper tbGoodsCategoryMapper;

    @Override
    public TbGoodsCategory findOneById(Long id) {
        return tbGoodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbGoodsCategory> findAll() {
        return tbGoodsCategoryMapper.selectByExample(null);
    }

    @Override
    public void add(TbGoodsCategory tbGoodsCategory) {

        if (tbGoodsCategory == null || StringUtils.isEmpty(tbGoodsCategory.getCategoryName())) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        tbGoodsCategory.setId(null);
        tbGoodsCategoryMapper.insert(tbGoodsCategory);

    }

    @Override
    public void update(TbGoodsCategory tbGoodsCategory) {

        if (tbGoodsCategory == null || tbGoodsCategory.getId() == null || StringUtils.isEmpty(tbGoodsCategory.getCategoryName())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbGoodsCategoryMapper.updateByPrimaryKey(tbGoodsCategory);

    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400,"参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbGoodsCategoryMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
