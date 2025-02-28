package br.com.springautomotors.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Spring Auto Motors - Rest API")
                                .description("Rest API FULL loja de carros")
                                .version("v 0.0.1")
                                .license(new License().name("").url(""))
                                .contact(new Contact().name("learn spring boot").email(""))
                );
    }

}