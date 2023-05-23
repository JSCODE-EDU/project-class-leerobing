package com.example.boardproject.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc              // spring-security와 연결할 때 이 부분이 없으면 404에러가 뜬다.
@EnableSwagger2            // 기능을 활성화하는 어노테이션입니다.
public class SwaggerConfig implements WebMvcConfigurer {
    

    /**
     * Swagger2 버전은 http://localhost:8080/swagger-ui.html
     * spring-security와 연결할 때 이 부분을 작성하지 않으면 404에러가 뜬다.
     *
     * 3.x 버전 부터는 swagger-ui 경로가 다르다고 합니다.
     * http://localhost:8080/swagger-ui/index.html 로 접근해보세요
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // -- Static resources
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

    }
}
