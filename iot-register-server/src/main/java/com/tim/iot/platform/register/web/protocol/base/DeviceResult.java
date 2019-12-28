package com.tim.iot.platform.register.web.protocol.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.tim.iot.platform.register.web.protocol.account.AccountInfo;
import com.tim.iot.platform.register.web.protocol.qrcode.QrCodeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Tell.Tim
 * @date : 2019/12/28 20:48
 * @fileName : DeviceResult
 */
@Data
@NoArgsConstructor
public class DeviceResult extends BaseResult{
    @JSONField(name = "qrCodeInfo")
    private QrCodeInfo qrCodeInfo;
    @JSONField(name = "accountInfo")
    private AccountInfo accountInfo;
}
