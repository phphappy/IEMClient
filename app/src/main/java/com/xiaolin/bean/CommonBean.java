package com.xiaolin.bean;

import java.io.Serializable;

/**
 * 登录返回 数据存储
 * Created by sjy on 2017/7/24.
 */

public class CommonBean implements Serializable {
    public String code;
    public String message;
    public LoginBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginBean getResult() {
        return result;
    }

    public void setResult(LoginBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result.toString() +
                '}';
    }

}
