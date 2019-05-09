package cn.kastner.chemiety.configuration;

import cn.kastner.chemiety.Interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/login", "/loginPage", "/errorPage");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
