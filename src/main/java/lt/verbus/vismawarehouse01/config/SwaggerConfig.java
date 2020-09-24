package lt.verbus.vismawarehouse01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String TAG_1 = "Products Services";

@Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("lt.verbus.vismawarehouse01.controller"))
            .paths(PathSelectors.any())
            .build()
            .tags(new Tag(TAG_1, "Use to view/modify products in warehouse"))
            .pathMapping("/")
            .apiInfo(metaData());
    }

    private ApiInfo metaData(){
    Contact contact = new Contact("Šarūnas Verbus", "https://www.verbus.lt", "sarunas@verbus.lt");

    return new ApiInfo(
            "Visma Warehouse Information System",
            "Simple informational app about the warehouse contents",
            "1.0",
            "Some kind of Terms of Service: ...",
            contact,
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());
    }

}
