package com.laughing.net.manager.base.gson;

/**
 * 与服务器约定好的统一返回格式
 * 作者：Laughing on 2018/6/5 16:31
 * 邮箱：719240226@qq.com
 */

public class BaseBean<T> {
    private boolean error;
    private T results;
    private String code = "-1";
    private String msg = "服务器统一错误";

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
