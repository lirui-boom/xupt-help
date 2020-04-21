package cn.edu.xupt.help.message.service;

import cn.edu.xupt.help.framework.domain.TbMessageCategory;

import java.util.List;

public interface MessageCategoryService {

    public abstract List<TbMessageCategory> findAll();

    public abstract TbMessageCategory findOneById(Long id);

    public abstract void add(TbMessageCategory tbMessageCategory);

    public abstract void update(TbMessageCategory tbMessageCategory);

    public abstract void deleteIds(Long[] ids);
}
