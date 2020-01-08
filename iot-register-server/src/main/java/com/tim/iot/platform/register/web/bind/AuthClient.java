package com.tim.iot.platform.register.web.bind;

import com.tim.iot.platform.register.web.bind.protocol.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Tell.Tim
 * @date : 2020/1/6 20:53
 * @fileName : AuthClient
 */
@FeignClient(url = "${auth.confirm.url}", name = "auth-confirm-client")
public interface AuthClient {

    @RequestMapping(method = RequestMethod.POST, value = "/${auth.confirm.path}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Auth.Result confirm(@RequestBody Auth.Param param);
}
