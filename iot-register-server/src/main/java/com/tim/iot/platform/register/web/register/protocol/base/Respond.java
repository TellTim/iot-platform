package com.tim.iot.platform.register.web.register.protocol.base;

/**
 * @author Tell.Tim
 * @fileName Respond
 * @date 2019/11/18 14:53
 */
public enum Respond {
    SUCCESS("200","success"),
    PARAM_EMPTY("3000","param empty"),
    BIND_EXIST("4000","bind exist"),
    BIND_NOT_EXIST("4001","bind not exist"),
    DEVICE_NOT_EXIST("5000","device not exist"),
    REGISTER_FAILURE("5001","device not exist"),
    ERROR("9000","error"),
    NET_ERROR("9001","net error"),
    TYPE_INVALID("9002","type not support");
    private String code;
    private String data;

    Respond(String code, String data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getData() {
        return data;
    }
}
