package com.hunter.iot.platform.register.domain.model.vojvalue;

/**
 * @author Tell.Tim
 * @program BindDemo
 * @packageName com.hunter.bind.domain.entity
 * @fileName ResponseCode
 * @date 2019/11/18 14:53
 * @description
 */
public enum ResponseCode {
    SUCCESS("200","success"),
    PARAM_EMPTY("3000","param empty"),
    BIND_EXIST("4000","bind exist"),
    BIND_NOT_EXIST("4001","bind not exist"),
    DEVICE_NOT_EXIST("5000","device not exist"),
    REGISTER_FAILURE("5001","device not exist"),
    ERROR("9000","error"),
    NET_ERROR("9001","net error");

    private String code;
    private String data;

    ResponseCode(String code, String data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
