package cn.edu.xupt.help.swiper.service.impl;

import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbSwiper;
import cn.edu.xupt.help.framework.domain.TbSwiperExample;
import cn.edu.xupt.help.swiper.mapper.TbSwiperMapper;
import cn.edu.xupt.help.swiper.service.SwiperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private TbSwiperMapper tbSwiperMapper;

    @Override
    public TbSwiper findOneById(Long id) {
        return tbSwiperMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbSwiper> findAll() {

        TbSwiperExample example = new TbSwiperExample();
        TbSwiperExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");

        return tbSwiperMapper.selectByExample(example);
    }

    @Override
    public void add(TbSwiper tbSwiper) {

        if (tbSwiper == null || StringUtils.isEmpty(tbSwiper.getSwiperImageUrl())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbSwiper.setStatus("0");
        tbSwiper.setSwiperId(null);

        tbSwiperMapper.insert(tbSwiper);
    }

    @Override
    public void update(TbSwiper tbSwiper) {

        if (tbSwiper == null || tbSwiper.getSwiperId() == null || StringUtils.isEmpty(tbSwiper.getSwiperImageUrl())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbSwiperMapper.updateByPrimaryKey(tbSwiper);
    }

    @Override
    public void updateStatusIds(Long[] ids, String status) {

        if (ids == null || ids.length == 0 || !"0".equals(status) && !"1".equals(status)) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            TbSwiper tbSwiper = findOneById(id);

            if (tbSwiper != null) {
                tbSwiper.setStatus(status);
                tbSwiperMapper.updateByPrimaryKey(tbSwiper);
            }
        }
    }

    @Override
    public void deleteIds(Long[] ids) {

        if (ids == null || ids.length == 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        for (Long id : ids) {

            if (findOneById(id) != null) {
                tbSwiperMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
