package com.tim.iot.platform.register.web.register.protocol.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.register.protocol.account.AccountInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tell.tim
 */
@Data
@NoArgsConstructor
public class BaseResult {
    private String code;
    private String data;

    @JSONField(name = "accountInfo")
    private AccountInfo accountInfo;

    public BaseResult(String code, String data) {
        this.code = code;
        this.data = data;
    }
}
