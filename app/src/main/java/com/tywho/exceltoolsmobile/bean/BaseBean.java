package com.tywho.exceltoolsmobile.bean;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 15:38
 */
public class BaseBean<T> {
    public int code;
    public String message;
    public T data;
    public String api;

    public BaseBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据 code 码判断业务状态
     * code == 10000，表示业务成功。为空表示登录成功 其他表示业务失败，message 进一步描述业务状态。
     */
    public boolean isSuccess() {
        return code == 10000 || code == 0;
    }

    /**
     * 登录超时
     *
     * @return
     */
    public boolean loginOut() {
        return code == 10002;
    }
}

