package cn.edu.xupt.help.dept.service;


import cn.edu.xupt.help.framework.domain.TbDept;

import java.util.List;

public interface DeptService {

    public abstract TbDept findOneById(Long id);

    public abstract List<TbDept> findByParentId(Long parentId);

    public abstract void add(TbDept tbDept);

    public abstract void update(TbDept tbDept);

    public abstract void deleteById(Long id);
}
