package vn.lgsp.fw.app.cmon.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;
import vn.lgsp.fw.app.cmon.domain.service.BaseServiceImpl;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@ComponentScan(basePackageClasses = { BaseServiceImpl.class })
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@PropertySource("classpath*:META-INF/env-config.properties")
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

}
