package cn.edu.xupt.help.swiper.service;

import cn.edu.xupt.help.framework.domain.TbSwiper;

import java.util.List;

public interface SwiperService {

    public abstract TbSwiper findOneById(Long id);

    public abstract List<TbSwiper> findAll();

    public abstract void  add(TbSwiper tbSwiper);

    public abstract void  update(TbSwiper tbSwiper);

    public abstract void updateStatusIds(Long[] ids, String status);

    public abstract void deleteIds(Long[] ids);

}
