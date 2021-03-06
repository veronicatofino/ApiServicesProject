package com.puj.admincenter.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.spring.web.paths.RelativePathProvider
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.ApiInfoBuilder

@Value(value = "\${api.host}")
private val apiHost: String? = null

@Value(value = "\${api.basepath}")
private val apiBasePath: String = "/"

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .host(apiHost)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.puj.admincenter.controller"))
        .paths(regex("/.*"))
        .build()
        .pathProvider(object : RelativePathProvider(null) {
            override fun getApplicationBasePath(): String {
                return apiBasePath
            }
        })
        .groupName("User Swagger")
        .apiInfo(defineApiMetaData())

    fun defineApiMetaData(): ApiInfo {
        return ApiInfoBuilder()
                .title("PUJ API AdminCenter")
                .description("\"PUJ RESTful API - AdminCenter\"")
                .version("1.1.0")
                .build();
    }
}
