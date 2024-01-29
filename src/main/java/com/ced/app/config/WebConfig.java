package com.ced.app.config;

import com.ced.app.config.interceptor.JwtInterceptor;
import com.ced.app.config.interceptor.ProfilInterceptor;

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

    @Bean
    public ProfilInterceptor profilInterceptor() {
        return new ProfilInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        // registry.addInterceptor(jwtInterceptor())
        //         .addPathPatterns("/annonces").excludePathPatterns(HttpMethod.GET.toString());

        // registry.addInterceptor(jwtInterceptor())
        //         .addPathPatterns("/annonces/except_user/");

        // registry.addInterceptor(jwtInterceptor())
        //         .addPathPatterns("/annonces/favoris/");

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/annonces/etat/lessthan/10");

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/annonces/{id}").excludePathPatterns(HttpMethod.GET.toString());

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Categorie/{id}")
        //     .excludePathPatterns(HttpMethod.GET.toString());

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Categorie/nbVente");            

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Categorie/nbVenteBetween");            

        // registry.addInterceptor(jwtInterceptor()).addPathPatterns("/historique_annonce/**");

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Marque/{id}")
        //     .excludePathPatterns(HttpMethod.GET.toString());

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Marque/nbVente");            

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/Marque/nbVenteBetween");  

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/modele").addPathPatterns();

        // registry.addInterceptor(jwtInterceptor()).addPathPatterns("/messages/**");

        // registry.addInterceptor(jwtInterceptor()).addPathPatterns("/soldeutilisateur/**");

        // registry.addInterceptor(profilInterceptor()).addPathPatterns("/solde/**");

        // registry.addInterceptor(jwtInterceptor()).addPathPatterns("/vente");

        // registry.addInterceptor(jwtInterceptor()).addPathPatterns("/ws/**");

    }
}
