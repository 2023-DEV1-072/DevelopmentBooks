package com.kata.developmentbooks.config;

import org.springframework.context.annotation.Bean;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("developmentbooks")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }
    private Predicate<String> postPaths() {
        return or(regex("/api/books.*"),regex("/books/"));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Books Catalogue API")
                .description("Kata Development Books api documentation")
                .termsOfServiceUrl("http://localhost:8080")
                .license("MIT")
                .licenseUrl("https://github.com/2023-DEV1-072/DevelopmentBooks/blob/main/LICENSE").version("1.0").build();
    }
}
