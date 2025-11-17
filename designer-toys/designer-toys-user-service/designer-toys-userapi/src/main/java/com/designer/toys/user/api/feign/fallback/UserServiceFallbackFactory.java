package com.designer.toys.user.api.feign.fallback;

import com.designer.toys.user.api.feign.IUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 */

@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<IUserServiceApi> {

    public IUserServiceApi create(Throwable cause) {
        return new IUserServiceApi() {

        };
    }
}
