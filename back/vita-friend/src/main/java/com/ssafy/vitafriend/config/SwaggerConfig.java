package com.ssafy.vitafriend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo commonInfo() {
        return new ApiInfoBuilder()
                .title("비타프렌즈 API")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket allApi() {
//        ParameterBuilder parameterBuilder = new ParameterBuilder();
//        parameterBuilder.name("userID")
//                .description("자신의 userID")
//                .modelRef(new ModelRef("String"))
//                .parameterType()

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Friend")
                .useDefaultResponseMessages(false)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.vitafriend.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(commonInfo());
    }
}
