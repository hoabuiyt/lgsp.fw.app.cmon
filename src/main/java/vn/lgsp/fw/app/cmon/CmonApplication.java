package vn.lgsp.fw.app.cmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import vn.lgsp.fw.app.cmon.domain.repository.BaseRepositoryImpl;
import vn.lgsp.fw.app.cmon.domain.service.BaseServiceImpl;
import vn.lgsp.fw.app.cmon.domain.service.ethnicity.CmonEthnicityService;
import vn.lgsp.fw.app.cmon.domain.service.ethnicity.CmonEthnicityServiceImpl;
import vn.lgsp.fw.app.cmon.web.api.ethnicity.CmonEthnicityRestController;


@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@ComponentScan(basePackageClasses = { BaseServiceImpl.class, CmonEthnicityRestController.class })
@SpringBootApplication
public class CmonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmonApplication.class, args);
	}
}
