package vn.lgsp.fw.app.cmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import vn.lgsp.fw.app.cmon.domain.entity.AuditorAwareImpl;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;
import vn.lgsp.fw.app.cmon.domain.service.BaseServiceImpl;
import vn.lgsp.fw.app.cmon.web.controller.ZkController;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@ComponentScan(basePackageClasses = { BaseServiceImpl.class, BaseRestController.class, ZkController.class })
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
//@EnableWebMvc
public class CmonApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CmonApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CmonApplication.class);
	}
	
	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
	
}
