package com.uniovi;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * This class is aimed to define the 
 * specific configuration for the application
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Configuration
public class CustomConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(
	    List<HandlerMethodArgumentResolver> argumentResolvers) {
	int[] values = new int[] { 0, 5 };
	PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
	resolver.setFallbackPageable(PageRequest.of(values[0], values[1]));
	argumentResolvers.add(resolver);
    }

    @Bean
    public LocaleResolver localeResolver() {
	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	localeResolver.setDefaultLocale(new Locale("es", "ES"));
	return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	localeChangeInterceptor.setParamName("lang");
	return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(localeChangeInterceptor());
    }
}
