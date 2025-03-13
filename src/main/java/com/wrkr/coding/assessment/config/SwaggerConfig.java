//package com.wrkr.coding.assessment.config;
//
//import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.customizers.OpenApiCustomiser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi addressBookApi() {
//        return GroupedOpenApi.builder()
//                .group("address-book-api")
//                .pathsToMatch("/api/addressbooks/**")
//                .build();
//    }
//
//    @Bean
//    public OpenApiCustomiser customOpenApi() {
//        return openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info().title("Address Book API").version("1.0").description("API documentation for the Address Book application"));
//    }
//}
