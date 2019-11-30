package com.hunter.iot.platform.register.web.protocol;

import com.hunter.iot.platform.register.web.protocol.base.BaseResult;
import lombok.Data;

public class Check {

    @Data
    public static class Param{
        private String deviceId;
    }

    @Data
    public static class Result extends BaseResult {
       private String account;
    }
}
