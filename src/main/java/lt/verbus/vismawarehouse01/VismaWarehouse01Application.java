package lt.verbus.vismawarehouse01;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VismaWarehouse01Application {

    public static void main(String[] args) {
        SpringApplication.run(VismaWarehouse01Application.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}")
                                         String appDescription,
                                 @Value("${application-version}")
                                         String appVersion) {

        Contact developerContact = new Contact();
        developerContact.setName("Šarūnas Verbus");
        developerContact.setUrl("https://www.verbus.lt");
        developerContact.setEmail("sarunas@verbus.lt");

        return new OpenAPI()
                .info(new Info()
                        .title("Warehouse Information System")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(developerContact)
                        .termsOfService("http://swagger.io/terms")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
