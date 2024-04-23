package org.dipl.rarefashion;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class WebConfig implements WebMvcConfigurer {

/*
    @Value("${images.location}")
    private String imagesLocation;
*/

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        configurer.setUseTrailingSlashMatch(true);
    }

/*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/ihimg/**")
                .addResourceLocations("file:/" + imagesLocation);
        log.debug("Added resource handler for /ihimg/** for location " + imagesLocation + " - " + registry.hasMappingForPattern("/ihimg/**"));
    }
*/

/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }
*/

    // i18n
/*
    @Bean
    public LocaleResolver localeResolver() {

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver("lang");
        cookieLocaleResolver.setDefaultLocale(Locale.forLanguageTag("bg"));
        cookieLocaleResolver.setCookieSecure(true);
        cookieLocaleResolver.setCookieMaxAge(Duration.ofMillis(31536000));
        return cookieLocaleResolver;
    }
*/

/*
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/messages", "classpath:/prices");
        messageSource.setBasenames("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
*/

/*
    @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {

        return CookieSameSiteSupplier.ofStrict();
    }
*/

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect());
//        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] permit_urls = {"/", "/fonts/**", "/css/**", "/img/**", "/js/**", "/products/**",
                "/error", "/404", "/500"};

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(permit_urls).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/actuator/**").authenticated()
                        .anyRequest().denyAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/")
                )
                .logout((logout) -> logout.logoutSuccessUrl("/"));

        //.sessionManagement(session -> session.maximumSessions(3));

        return http.build();
    }

/*
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("ih")
                .password("ih4488")
                .roles("ADMIN2")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
*/

    @Bean
    public MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter(ObjectMapper objectMapper) {
        var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    // bean for http session listener
    @Bean
    public HttpSessionListener httpSessionListener() {

        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {

                log.info("Session created: " + se.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {

                log.info("Session destroyed: " + se.getSession().getId());
            }
        };
    }
}
