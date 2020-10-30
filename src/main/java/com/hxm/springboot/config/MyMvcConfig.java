package com.hxm.springboot.config;

import com.hxm.springboot.component.LoginHandlerInterptor;
import com.hxm.springboot.component.MyLocalResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 * 扩展MVC，
 * 既保留了SpringBoot的自动默认配置
 * 又添加了用户自己扩展的配置
 */
//@EnableWebMvc//全面自己配置
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/hxm").setViewName("success");
    }

    @Bean
    public WebMvcConfigurerAdapter myAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                super.addInterceptors(registry);
                registry.addInterceptor(new LoginHandlerInterptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
