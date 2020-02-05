package edu.bjtu.crm.sso.web.config;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: Yimyl
 * @date: 2020/1/23 15:24
 * @description:
 * @modified By:
 * @version:
 */
@Configuration
@PropertySource({"classpath:freemarker.properties"})
public class FreeMarkerConfig {
    @Value("${spring.freemarker.request-context-attribute}")
    private String requestContent;

    @Value("${spring.freemarker.content-type}")
    private String contentType;

    @Value("${spring.freemarker.charset}")
    private String charset;

    @Value("${spring.freemarker.template-loader-path}")
    private String loaderPath;

    @Value("${spring.freemarker.suffix}")
    private String suffix;

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
        resolver.setRequestContextAttribute(requestContent);
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setContentType(contentType);
        resolver.setSuffix(suffix);
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths(loaderPath);
        factory.setDefaultEncoding(charset);
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();

        freemarker.template.Configuration configuration = factory.createConfiguration();
        configuration.setClassicCompatible(true);
        result.setConfiguration(configuration);
        Properties settings = new Properties();
        settings.put("template_update_delay", "0");
        settings.put("default_encoding", "UTF-8");
        settings.put("number_format", "0.##########");
        settings.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.put("classic_compatible", true);
        settings.put("template_exception_handler", "ignore");
        result.setFreemarkerSettings(settings);
        return result;
    }

}
