package com.laughing.net.manager.base;

/**
 * 友好的错误提示异常类
 * 作者：Laughing on 2018/6/5 15:36
 * 邮箱：719240226@qq.com
 */

public class ResultException extends RuntimeException {
    //默认是-1
    private String errorCode = "-1";


    /**
     * @param errorCode 用于程序判断的code
     * @param errorMsg  有好的提示
     */
    public ResultException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return super.toString() + ",ResultException{" +
                "errorCode='" + errorCode + '\'' +
                '}';
    }
}
