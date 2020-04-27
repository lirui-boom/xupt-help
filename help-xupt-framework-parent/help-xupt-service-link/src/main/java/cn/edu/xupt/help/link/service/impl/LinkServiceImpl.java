package cn.edu.xupt.help.link.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbLink;
import cn.edu.xupt.help.framework.domain.TbLinkExample;
import cn.edu.xupt.help.framework.domain.TbUser;
import cn.edu.xupt.help.link.mapper.TbLinkMapper;
import cn.edu.xupt.help.link.mapper.TbUserMapper;
import cn.edu.xupt.help.link.service.LinkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    private TbLinkMapper tbLinkMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbLink findOneById(Long id) {
        return tbLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbLink> findAllLink(Long userId1, Long userId2) {

        if (userId1 == null || userId2 == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        TbLinkExample example1 = new TbLinkExample();
        TbLinkExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andFromUidEqualTo(userId1);
        criteria1.andToUidEqualTo(userId2);
        List<TbLink> linkList1 = tbLinkMapper.selectByExample(example1);

        TbLinkExample example2 = new TbLinkExample();
        TbLinkExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andFromUidEqualTo(userId2);
        criteria2.andToUidEqualTo(userId1);
        List<TbLink> linkList2 = tbLinkMapper.selectByExample(example2);

        linkList1.addAll(linkList2);

        Collections.sort(linkList1, new Comparator<TbLink>() {
            @Override
            public int compare(TbLink link1, TbLink link2) {
                return link1.getCreateTime().compareTo(link2.getCreateTime());
            }
        });

        return linkList1;
    }

    @Override
    public void add(TbLink tbLink) {

        if (tbLink == null || tbLink.getFromUid() == null || tbLink.getToUid() == null ||
                StringUtils.isEmpty(tbLink.getContent())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }


        if (tbUserMapper.selectByPrimaryKey(tbLink.getFromUid()) == null ||
                tbUserMapper.selectByPrimaryKey(tbLink.getToUid()) == null) {
            ExceptionCast.cast(ResponseResult.build(400, "用户不存在！"));
        }

        tbLink.setId(null);
        tbLink.setCreateTime(new Date());
        tbLinkMapper.insert(tbLink);

    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbLinkMapper.deleteByPrimaryKey(id);
            }
        }

    }
}
