package com.demo.emsp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 只是对文件路径进行了拦截
 * 其他接口和网络页面默认就是通过的
 *
 * @author muyoufang
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 实现拦截器 要拦截的路径以及不拦截的路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        /*
        SSOSpringInterceptor ssoInterceptor = new SSOSpringInterceptor();
        ssoInterceptor.setHandlerInterceptor(new LoginHandlerInterceptor());

        registry.addInterceptor(ssoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/file/**",
                        "/admin/login.html",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                );
        */
    }

    /**
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("*");
    }
}
