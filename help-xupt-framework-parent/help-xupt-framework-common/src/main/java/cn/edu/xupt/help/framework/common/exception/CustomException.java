package cn.edu.xupt.help.framework.common.exception;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * 自定义异常
 */
@Component
public class CustomException extends RuntimeException {

    //错误代码
    private ResponseResult responseResult;

    public CustomException(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }
}
