package cn.edu.xupt.help.dept.service.impl;

import cn.edu.xupt.help.dept.mapper.TbDeptMapper;
import cn.edu.xupt.help.dept.service.DeptService;
import cn.edu.xupt.help.framework.common.exception.ExceptionCast;
import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import cn.edu.xupt.help.framework.domain.TbDept;
import cn.edu.xupt.help.framework.domain.TbDeptExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private TbDeptMapper tbDeptMapper;

    @Override
    public TbDept findOneById(Long id) {
        return tbDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbDept> findByParentId(Long parentId) {

        TbDeptExample example = new TbDeptExample();
        TbDeptExample.Criteria criteria = example.createCriteria();
        criteria.andDeptParentEqualTo(parentId);
        return tbDeptMapper.selectByExample(example);
    }

    @Override
    public void add(TbDept tbDept) {

        if (tbDept == null || tbDept.getDeptParent() == null || StringUtils.isEmpty(tbDept.getDeptName())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (findOneById(tbDept.getDeptParent()) == null && tbDept.getDeptParent().intValue() != 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        tbDeptMapper.insert(tbDept);
    }

    @Override
    public void update(TbDept tbDept) {

        if (tbDept == null || tbDept.getDeptParent() == null || tbDept.getId() == null || StringUtils.isEmpty(tbDept.getDeptName())) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (findOneById(tbDept.getDeptParent()) == null && tbDept.getDeptParent().intValue() != 0) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (findOneById(tbDept.getId()) == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该部门不存在！"));
        }

        tbDeptMapper.updateByPrimaryKey(tbDept);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            ExceptionCast.cast(ResponseResult.build(400, "参数不合法！"));
        }

        if (findOneById(id) == null) {
            ExceptionCast.cast(ResponseResult.build(400, "该部门不存在！"));
        }

        TbDeptExample example = new TbDeptExample();
        TbDeptExample.Criteria criteria = example.createCriteria();
        criteria.andDeptParentEqualTo(id);

        List<TbDept> tbDepts = tbDeptMapper.selectByExample(example);

        if (tbDepts != null && tbDepts.size() > 0) {
            ExceptionCast.cast(ResponseResult.build(400, "不能删除:该部门下含有子部门，请删除下面的子部门后再删除此部门！"));
        }

        tbDeptMapper.deleteByPrimaryKey(id);
    }
}
