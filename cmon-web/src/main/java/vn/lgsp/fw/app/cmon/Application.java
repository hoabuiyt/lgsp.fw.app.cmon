package vn.lgsp.fw.app.cmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import vn.lgsp.fw.app.cmon.domain.AuditorAwareImpl;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	/*
	@Bean
	public ViewResolver zulViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/zul/",  ".zul");
		resolver.setOrder(InternalResourceViewResolver.LOWEST_PRECEDENCE);
		return resolver;
	}
	*/
}


