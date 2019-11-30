package com.hunter.iot.platform.register.global.config;

import com.hunter.iot.platform.register.domain.repo.IDeviceRepo;
import com.hunter.iot.platform.register.domain.repo.impl.DeviceRepoImpl;
import com.hunter.iot.platform.register.domain.service.IDeviceService;
import com.hunter.iot.platform.register.domain.service.IQrCodeGenerate;
import com.hunter.iot.platform.register.domain.service.impl.DeviceServiceImpl;
import com.hunter.iot.platform.register.domain.service.impl.QrCodeGenerateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IDeviceRepo deviceRepo(){
        return new DeviceRepoImpl();
    }

    @Bean IQrCodeGenerate qrCodeGenerate(){
        return new QrCodeGenerateImpl();
    }

    @Bean
    public IDeviceService deviceService(IDeviceRepo deviceRepo, IQrCodeGenerate qrCodeGenerate){
        return new DeviceServiceImpl(deviceRepo,qrCodeGenerate);
    }
}
