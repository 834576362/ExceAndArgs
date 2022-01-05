package org.zfjava.app.exceptionhanadler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zfjava.app.msg.ResponseInfo;

import java.nio.file.AccessDeniedException;

/**
 * @auther zhangfen
 * @date 2022/1/4 17:11
 * @Des 新建一个 ExceptionHandlerAdvice 全局异常处理类，
 *      然后加上 @RestControllerAdvice 注解即可拦截项目中抛出的异常，
 *      如下代码中包含了几个异常处理，如参数格式异常、参数缺失、系统异常等
 */


/*
@RestControllerAdvice 注解包含了 @Component 注解，说明在 Spring Boot 启动时，也会把该类作为组件交给 Spring 来管理。

@RestControllerAdvice 注解包含了 @ResponseBody 注解，为了异常处理完之后给调用方输出一个 JSON 格式的封装数据。

@RestControllerAdvice 注解还有个 basePackages 属性，该属性用来拦截哪个包中的异常信息，一般我们不指定这个属性，我们拦截项目工程中的所有异常。

在方法上通过 @ExceptionHandler 注解来指定具体的异常，然后在方法中处理该异常信息，最后将结果通过统一的 JSON 结构体返回给调用者。

但在项目中，我们一般都会比较详细地去拦截一些常见异常，拦截 Exception 虽然可以一劳永逸，但是不利于我们去排查或者定位问题。
实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler 最下面，如果都没有找到，最后再拦截一下 Exception 异常，保证输出信息友好。
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    //参数格式异常
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo badRequestException(IllegalArgumentException exception){
        log.error("参数格式不合法：" + exception.getMessage());
        return new ResponseInfo(HttpStatus.BAD_REQUEST.value(), "参数格式不符！");
    }


    // 权限不足异常处理
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseInfo badRequestException(AccessDeniedException exception) {
        return new ResponseInfo(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    // 参数缺失异常处理
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo badRequestException(Exception exception) {
        return new ResponseInfo(HttpStatus.BAD_REQUEST.value(), "缺少必填参数！");
    }

    // 空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        return new ResponseInfo("500", "空指针异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new ResponseInfo("500", "系统发生异常，请联系管理员");
    }

    // 系统异常处理
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo exception(Throwable throwable) {
        log.error("系统异常", throwable);
        return new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR.value() + "系统异常，请联系管理员！");
    }
}
