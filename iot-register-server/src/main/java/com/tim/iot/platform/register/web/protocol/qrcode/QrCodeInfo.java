package com.tim.iot.platform.register.web.protocol.qrcode;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author : Tell.Tim
 * @date : 2019/12/28 13:41
 * @fileName : QrCodeInfo
 */
@Data
public class QrCodeInfo {
    @JSONField(name = "qrCode")
    private String qrCode;
    @JSONField(name = "expireIn")
    private int expireIn;
}
