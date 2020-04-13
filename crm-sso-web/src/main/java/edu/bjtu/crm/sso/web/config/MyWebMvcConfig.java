package edu.bjtu.crm.sso.web.config;

import edu.bjtu.crm.sso.web.interceptor.UserInfoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private UserInfoInterceptor userInfoInterceptor;

    @Bean
    public MyWebMvcConfig getMyWebMvcConfig() {
        MyWebMvcConfig myWebMvcConfig = new MyWebMvcConfig() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("alogin");
//                registry.addViewController("/login").setViewName("alogin");
//                registry.addViewController("/main.html").setViewName("dashboard");
//            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(userInfoInterceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login", "/doLogin","/register",  "/doRegister","/valid-code", "/refresh-valid-code");
            }
        };
        return myWebMvcConfig;
    }
}