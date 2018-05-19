package vn.lgsp.fw.app.cmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;
import vn.lgsp.fw.app.cmon.domain.service.BaseServiceImpl;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;


@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@ComponentScan(basePackageClasses = { BaseServiceImpl.class, BaseRestController.class })
@SpringBootApplication
public class CmonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmonApplication.class, args);
	}
}
