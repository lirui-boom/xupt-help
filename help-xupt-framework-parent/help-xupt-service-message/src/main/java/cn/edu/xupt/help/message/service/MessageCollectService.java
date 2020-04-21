package cn.edu.xupt.help.message.service;

import cn.edu.xupt.help.framework.domain.TbMessageCollect;

import java.util.List;

public interface MessageCollectService {

    public abstract TbMessageCollect findOneById(Long id);

    public abstract List<TbMessageCollect> findMessageCollectByUserId(Long userId);

    public abstract void add(TbMessageCollect tbMessageCollect);

    public abstract void deleteIds(Long[] ids);
}
