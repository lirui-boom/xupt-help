package cn.edu.xupt.help.test.service.impl;

import cn.edu.xupt.help.framework.domain.TbUser;
import cn.edu.xupt.help.test.mapper.TbUserMapper;
import cn.edu.xupt.help.test.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> findAll() {
        return tbUserMapper.selectByExample(null);
    }
}
