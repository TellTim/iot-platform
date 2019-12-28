package com.tim.iot.platform.register.global.aop.web;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    private static String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String logStr = "";
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            logStr += "\n[url   ]:" + request.getRequestURI();
            logStr += "\n[method]:" + joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();

            String[] params = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();

            Object[] args = joinPoint.getArgs();
            int i = 0;
            for (Object arg : args) {
                if (arg == request || arg == response) {
                    i += 1;
                    continue;
                }
                String typeName;
                try {
                    typeName = arg.getClass().getTypeName();
                    if (!Arrays.asList(types).contains(typeName)) {
                        // 把参数转成json格式
                        logStr += "\n[param ]:" + JSONObject.toJSONString(arg);
                    } else {
                        logStr += "\n[param ]:" + arg;
                    }
                    i += 1;
                } catch (Exception e) {
                    log.warn("aop error when analysis arg " + arg);
                }
            }
            log.info(logStr + "\n");
        } catch (Throwable e) {
            log.warn("doBefore aop error.", e.getCause());
        }
    }


    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) throws Throwable {
        if (response != null) {
            String typeName;
            try {
                typeName = response.getClass().getTypeName();
                if (!Arrays.asList(types).contains(typeName)) {
                    log.info("\n[response parameter ]: " + JSONObject.toJSONString(response) + "\n");
                } else {
                    log.info("\n[response parameter ]: " + response.toString() + "\n");
                }
            } catch (Exception e) {
                log.warn("doAfterReturning aop error.", e);
            }
        }
    }
}
