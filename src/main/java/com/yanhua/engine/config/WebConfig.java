/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: WebConfig
 * Author: Emiya
 * Date: 2020/9/21 22:17
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.config;

import com.yanhua.engine.common.dto.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈功能简述〉<br>
 * 〈〉
 *  <p>
 * @author Emiya
 * @create 2020/9/21 22:17
 * @version 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ResponseResultInterceptor resultInterceptor;
    public WebConfig(ResponseResultInterceptor resultInterceptor) {
        this.resultInterceptor = resultInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resultInterceptor);
//                .addPathPatterns("/api/**");

    }
}