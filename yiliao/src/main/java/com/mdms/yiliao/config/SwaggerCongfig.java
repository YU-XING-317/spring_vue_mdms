package com.mdms.yiliao.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration      // 告诉spring容器，此为配置类
@EnableSwagger2     // 启用Swagger2
public class SwaggerCongfig {
    // 配置 Swagger2 相关的 bean
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com")) //com包下所有api交由Swagger2管理
                .paths(PathSelectors.any()).build();
    }

    // API文档页面显示信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("医疗设备管理项目")
                .build();
    }
}
