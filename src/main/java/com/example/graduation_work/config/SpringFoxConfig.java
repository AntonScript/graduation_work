package com.example.graduation_work.config;

import com.example.graduation_work.GraduationWorkApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.annotation.PostConstruct;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
@Slf4j
public class SpringFoxConfig {

    private static final String LOG_TAG = "[SWAGGER_CONFIGURATION] ::";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(GraduationWorkApplication.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build();
    }

    @PostConstruct
    public void init() {
        log.debug(
            "{} инициализирована в {} профиле",
            LOG_TAG
        );
    }
}
