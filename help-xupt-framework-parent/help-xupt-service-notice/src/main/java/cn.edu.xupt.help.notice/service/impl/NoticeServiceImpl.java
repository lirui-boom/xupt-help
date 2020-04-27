package cn.edu.xupt.help.notice.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.PageResult;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbNotice;
import cn.edu.xupt.help.framework.domain.TbNoticeExample;
import cn.edu.xupt.help.notice.mapper.TbNoticeMapper;
import cn.edu.xupt.help.notice.service.NoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private TbNoticeMapper tbNoticeMapper;

    @Override
    public List<TbNotice> findAll() {

        TbNoticeExample example = new TbNoticeExample();
        TbNoticeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        return tbNoticeMapper.selectByExample(example);
    }

    @Override
    public TbNotice findOneById(Long id) {
        return tbNoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult search(int pageNum, int pageSize, TbNotice tbNotice) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        TbNoticeExample example = new TbNoticeExample();
        TbNoticeExample.Criteria criteria = example.createCriteria();

        if (tbNotice.getId() != null) {
            criteria.andIdEqualTo(tbNotice.getId());
        }

        if (!StringUtils.isEmpty(tbNotice.getContent())) {
            criteria.andContentLike("%" + tbNotice.getContent() + "%");
        }

        if (!StringUtils.isEmpty(tbNotice.getStatus())) {
            criteria.andStatusEqualTo(tbNotice.getStatus());
        }

        Page page = (Page) tbNoticeMapper.selectByExample(example);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbNotice tbNotice) {

        if (tbNotice == null || StringUtils.isEmpty(tbNotice.getContent())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbNotice.setId(null);
        tbNotice.setCreateTime(new Date());
        tbNotice.setStatus("0");

        tbNoticeMapper.insert(tbNotice);
    }

    @Override
    public void update(TbNotice tbNotice) {

        if (tbNotice == null || StringUtils.isEmpty(tbNotice.getContent()) || tbNotice.getId() == null ||
                StringUtils.isEmpty(tbNotice.getStatus())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbNoticeMapper.updateByPrimaryKey(tbNotice);

    }

    @Override
    public void updateStatusIds(Long[] ids, String status) {

        if (ids == null || ids.length == 0 || !"0".equals(status) && !"1".equals(status)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            TbNotice tbNotice = findOneById(id);

            if (tbNotice != null) {
                tbNotice.setStatus(status);
                tbNoticeMapper.updateByPrimaryKey(tbNotice);
            }
        }
    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbNoticeMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
