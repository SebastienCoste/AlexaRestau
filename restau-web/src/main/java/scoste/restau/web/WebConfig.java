package scoste.restau.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import scoste.restau.web.configuration.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    public final static String EVENT_ID = "id";
    public final static String EVENT_TIMESTAMP = "eventTime";

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        addDefaultHttpMessageConverters(converters);

        AbstractJackson2HttpMessageConverter jsonConverter = (AbstractJackson2HttpMessageConverter) converters.stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);

        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        objectMapper.registerModule(createTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private Module createTimeModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Instant.class, new InstantJsonSerializer());
        module.addDeserializer(Instant.class, new InstantJsonDeserializer());
        module.addSerializer(LocalDate.class, new LocalDateJsonSerializer());
        return module;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new EventIdResolver());
        argumentResolvers.add(new EventTimeResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (swaggerEnabled) {
            registry
                    .addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry
                    .addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.FRANCE);
        return resolver;
    }
}

