package com.lvgr.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvgr
 * @date 2021/3/10 10:28
 * @desc:统一返回结果
 */
@Data
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回状态")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }


    public Result code(Integer code){
        this.setCode(code);
        return this;
    }


    public Result message(String message){
        this.setMessage(message);
        return this;
    }


    public Result data(String key,Object value){
        this.data.put(key, value);
        return this;
    }


    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
