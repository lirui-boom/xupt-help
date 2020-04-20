package cn.edu.xupt.help.framework.common.exception;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * 自定义异常抛出类
 */
@Component
public class ExceptionCast {

    public static void cast(ResponseResult responseResult) {
        throw new CustomException(responseResult);
    }
}
