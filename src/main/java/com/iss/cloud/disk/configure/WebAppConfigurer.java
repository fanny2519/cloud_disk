package com.iss.cloud.disk.configure;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *功能描述
 * @author tangfl
 * @description
 * @date
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，/**是对所有的请求都做拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login", "/register","/user/registerUser","/index","/bootstrap/**");
    }


}