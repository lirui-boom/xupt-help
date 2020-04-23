package cn.edu.xupt.help.goods.controller;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbGoodsCategory;
import cn.edu.xupt.help.goods.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"商品分类管理"})
@RestController
@RequestMapping("goods/category")
public class GoodsCategoryController{

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @ApiOperation("根据id查询商品分类")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(goodsCategoryService.findOneById(id));
    }

    @ApiOperation("查询所有商品分类")
    @GetMapping("findAll")
    public ResponseResult findAll() {
        return ResponseResult.ok(goodsCategoryService.findAll());
    }

    @ApiOperation("添加商品分类")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbGoodsCategory tbGoodsCategory) {
        goodsCategoryService.add(tbGoodsCategory);
        return ResponseResult.ok();
    }

    @ApiOperation("修改商品分类")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbGoodsCategory tbGoodsCategory) {
        goodsCategoryService.update(tbGoodsCategory);
        return ResponseResult.ok();
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping("deleteIds")
    public ResponseResult deleteIds(Long[] ids) {
        goodsCategoryService.deleteIds(ids);
        return ResponseResult.ok();
    }
}
