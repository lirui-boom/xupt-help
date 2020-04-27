package cn.edu.xupt.help.notice.service;


import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.domain.TbNotice;

import java.util.List;

public interface NoticeService {

    public abstract List<TbNotice> findAll();

    public abstract TbNotice findOneById(Long id);

    public abstract PageResult search(int pageNum, int pageSize, TbNotice tbNotice);

    public abstract void add(TbNotice tbNotice);

    public abstract void update(TbNotice tbNotice);

    public abstract void updateStatusIds(Long[] ids, String status);

    public abstract void deleteIds(Long[] ids);
}
