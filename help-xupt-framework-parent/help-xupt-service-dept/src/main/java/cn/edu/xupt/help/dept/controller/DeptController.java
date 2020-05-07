package cn.edu.xupt.help.dept.controller;

import cn.edu.xupt.help.dept.service.DeptService;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbDept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"部门接口"})
@RestController
@RequestMapping("dept")
public class DeptController{

    @Autowired
    private DeptService deptService;

    @ApiOperation("根据id查询部门")
    @GetMapping("findOneById")
    public ResponseResult findOneById(Long id) {
        return ResponseResult.ok(deptService.findOneById(id));
    }

    @ApiOperation("根据id查询此部门下的所有子部门")
    @GetMapping("findByParentId")
    public ResponseResult findByParentId(Long parentId) {
        return ResponseResult.ok(deptService.findByParentId(parentId));
    }

    @ApiOperation("新增部门")
    @PostMapping("add")
    public ResponseResult add(@RequestBody TbDept tbDept) {
        deptService.add(tbDept);
        return ResponseResult.ok();
    }

    @ApiOperation("修改部门")
    @PutMapping("update")
    public ResponseResult update(@RequestBody TbDept tbDept) {
        deptService.update(tbDept);
        return ResponseResult.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("deleteById")
    public ResponseResult deleteById(Long id) {
        deptService.deleteById(id);
        return ResponseResult.ok();
    }
}
