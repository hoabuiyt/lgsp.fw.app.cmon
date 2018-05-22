package vn.lgsp.fw.app.cmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import vn.lgsp.fw.app.cmon.domain.entity.AuditorAwareImpl;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;
import vn.lgsp.fw.app.cmon.domain.service.BaseServiceImpl;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@ComponentScan(basePackageClasses = { BaseServiceImpl.class, BaseRestController.class })
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class CmonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmonApplication.class, args);
	}
	
	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
	
}
