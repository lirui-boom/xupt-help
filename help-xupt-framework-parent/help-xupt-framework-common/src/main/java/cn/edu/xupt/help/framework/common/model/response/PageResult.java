package cn.edu.xupt.help.framework.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@ApiModel(value="PageResult对象",description="分页查询结果对象")
@Component
public class PageResult implements Serializable {

    @ApiModelProperty(name="total",value="结果总数")
    private Long total;
    @ApiModelProperty(name="total",value="结果数据列表")
    private List rows;

    public PageResult() {
    }

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
