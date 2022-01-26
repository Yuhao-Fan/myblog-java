package com.fan.common.lang;

import lombok.Data;

import java.io.Serializable;
@Data
public class Result implements Serializable {
    private int code;//200正常
    private String msg;
    private Object data;
    public static Result succ(Object data) {
        Result r =new Result();
        r.setCode(200);
        r.setMsg("操作成功");
        r.setData(data);

        return r;
    }
    public static Result succ(String msg,Object data) {
        Result r =new Result();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }
    public static Result succ(String msg) {
        Result r =new Result();
        r.setCode(200);
        r.setData(null);
        r.setMsg(msg);


        return r;
    }
    public static Result fail(String msg) {
        Result r =new Result();
        r.setCode(404);
        r.setData(null);
        r.setMsg(msg);


        return r;
    }
    public static Result fail(String msg,Object data) {
        Result r =new Result();
        r.setCode(404);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }
    public static Result fail(Integer code,String msg,Object data) {
        Result r =new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);

        return r;
    }


}
