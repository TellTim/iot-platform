package com.tim.iot.platform.register.domain.service.impl;

import com.tim.iot.platform.register.domain.service.IQrCodeGenerate;
import com.tim.iot.platform.register.web.register.protocol.exception.NotSupportDeviceTypeException;


public class QrCodeGenerateImpl implements IQrCodeGenerate {

    @Override
    public String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException {
        if (DeviceType.TYPE_A.getType().equals(type)) {
            return String.format(
                    "sn=%s&mac=%s&imei=%s&devType=%s&t=%s",
                     deviceId, mac, imei,
                    DeviceType.TYPE_A.getProperty(), timestamp.toString());
        }
        if (DeviceType.TYPE_B.getType().equals(type)) {
            return String.format(
                    "sn=%s&mac=%s&imei=%s&devType=%s&t=%s",
                     deviceId, mac, imei,
                    DeviceType.TYPE_B.getProperty(), timestamp.toString());
        }
        throw new NotSupportDeviceTypeException("not support device type");
    }

    enum DeviceType{
        /**
         * 产品类型,三方应用提供的产品类型属性
         */
        TYPE_A("type-a","aaaaaaa"),
        TYPE_B("type-b","bbbbbbb");

        private String type;
        private String property;

        DeviceType(String type, String property) {
            this.type = type;
            this.property = property;
        }


        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
