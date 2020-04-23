package cn.edu.xupt.help.goods.controller;


import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbGoods;
import cn.edu.xupt.help.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"商品管理"})
@RestController
@RequestMapping("/goods")
public class GoodsController{

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("根据id查询商品")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(goodsService.findOneById(id));
    }

    @ApiOperation("条件分页查询商品")
    @PostMapping("search")
    public ResponseResult search(int pageNum, int pageSize, @RequestBody TbGoods tbGoods) {
        return ResponseResult.ok(goodsService.search(pageNum,pageSize,tbGoods));
    }

    @ApiOperation("添加商品")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbGoods tbGoods) {
        goodsService.add(tbGoods);
        return ResponseResult.ok();
    }

    @ApiOperation("修改商品信息")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbGoods tbGoods) {
        goodsService.update(tbGoods);
        return ResponseResult.ok();
    }

    @ApiOperation("修改商品状态")
    @PutMapping("updateStatus")
    public ResponseResult updateStatus(Long id, String status) {
        goodsService.updateStatus(id,status);
        return ResponseResult.ok();
    }

    @ApiOperation("删除商品")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        goodsService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
