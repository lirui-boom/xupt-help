package cn.edu.xupt.help.swiper.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbSwiper;
import cn.edu.xupt.help.swiper.service.SwiperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"轮播图接口"})
@RestController
@RequestMapping("swiper")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    @ApiOperation("查询所有发布的轮播图")
    @GetMapping("findAll")
    public ResponseResult findAll() {
        return ResponseResult.ok(swiperService.findAll());
    }


    @ApiOperation("根据id查询轮播图信息")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(swiperService.findOneById(id));
    }

    @ApiOperation("新增轮播图信息")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbSwiper tbSwiper) {
        swiperService.add(tbSwiper);
        return ResponseResult.ok();
    }

    @ApiOperation("修改轮播图信息")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbSwiper tbSwiper) {
        swiperService.update(tbSwiper);
        return ResponseResult.ok();
    }

    @ApiOperation("修改轮播图状态")
    @PutMapping("updateStatusIds")
    public ResponseResult updateStatusIds(Long[] ids, String status) {
        swiperService.updateStatusIds(ids, status);
        return ResponseResult.ok();
    }

    @ApiOperation("删除轮播图")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        swiperService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
