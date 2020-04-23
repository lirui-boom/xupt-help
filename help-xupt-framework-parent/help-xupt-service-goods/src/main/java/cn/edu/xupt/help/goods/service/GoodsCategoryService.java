package cn.edu.xupt.help.goods.service;

import cn.edu.xupt.help.framework.domain.TbGoodsCategory;

import java.util.List;

public interface GoodsCategoryService {

    public abstract TbGoodsCategory findOneById(Long id);

    public abstract List<TbGoodsCategory> findAll();

    public abstract void add(TbGoodsCategory tbGoodsCategory);

    public abstract void update(TbGoodsCategory tbGoodsCategory);

    public abstract void deleteIds(Long[] ids);
}
