package cn.edu.xupt.help.test.service;

import cn.edu.xupt.help.framework.domain.TbUser;

import java.util.List;

public interface UserService {

    public abstract List<TbUser> findAll();
}
