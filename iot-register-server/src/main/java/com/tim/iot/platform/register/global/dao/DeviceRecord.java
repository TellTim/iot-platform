package com.tim.iot.platform.register.global.dao;

public class DeviceRecord {
    private Long id;

    private String deviceId;

    private String macAddress;

    private String imei;

    private Long createAt;

    private Boolean bindFlag;

    private String account;

    public DeviceRecord(Long id, String deviceId, String macAddress, String imei, Long createAt, Boolean bindFlag, String account) {
        this.id = id;
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.imei = imei;
        this.createAt = createAt;
        this.bindFlag = bindFlag;
        this.account = account;
    }

    public DeviceRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Boolean getBindFlag() {
        return bindFlag;
    }

    public void setBindFlag(Boolean bindFlag) {
        this.bindFlag = bindFlag;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}