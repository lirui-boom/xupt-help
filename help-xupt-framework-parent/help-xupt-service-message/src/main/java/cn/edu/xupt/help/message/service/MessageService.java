package cn.edu.xupt.help.message.service;

import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.domain.TbMessage;

public interface MessageService {

    public abstract TbMessage findOneById(Long id);

    public abstract PageResult search(int pageNum,int pageSize,TbMessage tbMessage);

    public abstract void add(TbMessage tbMessage);

    public abstract void update(TbMessage tbMessage);

    public abstract void updateStatus(Long id, String status);

    public abstract void deleteIds(Long[] ids);
}
