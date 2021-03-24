package com.lvgr.edubase.exceptionhandle;

import com.lvgr.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvgr
 * @date 2021/3/10 21:29
 * @desc 异常处理
 * @Slf4j 把异常信息输出加入到日志里面
 * @ControllerAdvice Controller增强器，作用是给Controller控制器添加统一的操作或处理，用法是结合@ExceptionHandler用于全局异常的处理
 */

@ControllerAdvice
@Slf4j
public class ClobalExceptionHandle {

    /**
     * @ExceptionHandler(Exception.class):拦截异常并统一处理 不管出现什么异常都会经过下面定义的方法
     * ResponseBody
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行全局异常");
    }

    /**
     * @ExceptionHandler(EduException.class):自定义异常
     * ResponseBody
     * @param e
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e){
        String stackTrace = ExceptionUtil.getStackTrace(e);
        log.error(stackTrace);
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
