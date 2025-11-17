package com.designer.toys.product.api.feign.fallback;

import com.designer.toys.product.api.feign.IProductServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 */
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<IProductServiceApi> {

    @Override
    public IProductServiceApi create(Throwable cause) {
        return new IProductServiceApi() {

        };
    }
}
