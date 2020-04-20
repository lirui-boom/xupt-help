package cn.edu.xupt.help.framework.common.exception;

import cn.edu.xupt.help.framework.common.model.response.ResponseResult;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获类
 */
@Component
@RestControllerAdvice
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>,ResponseResult> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResponseResult> builder = ImmutableMap.builder();

    static {
        //定义异常类型所对应的错误代码
        builder.put(AccessDeniedException.class,ResponseResult.build(403,"权限不足！"));
    }

    /**
     * 自定义异常捕获
     *
     * @param customException
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException) {
        return customException.getResponseResult();
    }

    /**
     * 不可预知异常捕获
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception) {
        //日志记录
        LOGGER.error(" catch exception : {} ", exception.getMessage());

        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResponseResult responseResult = EXCEPTIONS.get(exception.getClass());

        if (responseResult != null) {
            return responseResult;
        }

        return ResponseResult.build(99999,"服务器正忙，请稍后重试！");
    }
}

