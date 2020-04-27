package cn.edu.xupt.help.link.service;

import cn.edu.xupt.help.framework.domain.TbLink;

import java.util.List;

public interface LinkService {

    public abstract TbLink findOneById(Long id);

    public abstract List<TbLink> findAllLink(Long userId1, Long userId2);

    public abstract void add(TbLink tbLink);

    public abstract void deleteIds(Long[] ids);

}
