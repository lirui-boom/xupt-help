package cn.edu.xupt.help.goods.service;

import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.domain.TbGoods;

public interface GoodsService {

    public abstract TbGoods findOneById(Long id);

    public abstract PageResult search(int pageNum, int pageSize, TbGoods tbGoods);

    public abstract void add(TbGoods tbGoods);

    public abstract void update(TbGoods tbGoods);

    public abstract void updateStatus(Long id, String status);

    public abstract void deleteIds(Long[] ids);
}
