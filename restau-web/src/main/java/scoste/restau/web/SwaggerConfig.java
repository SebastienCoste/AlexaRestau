package scoste.restau.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class SwaggerConfig {


    @Bean
    public Docket docket() {
        Parameter consumerId = new ParameterBuilder()
                .name(WebConfig.EVENT_ID)
                .description("event id")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();

        Parameter consumerTime = new ParameterBuilder()
                .name(WebConfig.EVENT_TIMESTAMP)
                .description("event timestamp")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();

        return new Docket(SWAGGER_2)
                .globalOperationParameters(Arrays.asList(consumerId, consumerTime))
                .select()
                .apis(RequestHandlerSelectors.basePackage("scoste.restau.web"))
                .paths(PathSelectors.any())
                .build();
    }
}

