package com.ced.app.config;

import com.ced.app.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/annonces").excludePathPatterns(HttpMethod.GET.toString());

        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/Categorie").excludePathPatterns(HttpMethod.POST.toString());
    }
}
