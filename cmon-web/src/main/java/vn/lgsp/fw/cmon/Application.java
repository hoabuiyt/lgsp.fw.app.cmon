package vn.lgsp.fw.cmon;

import java.util.Arrays;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import vn.lgsp.fw.cmon.domain.AuditorAwareImpl;
import vn.lgsp.fw.cmon.web.CacheFilter;
import vn.lgsp.fw.core.BaseRepositoryImpl;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	
	private String keystorePath = "resource:wso2carbon.jks";
	private String keystorePassword = "wso2carbon";
	private String privateKeyPassword = "wso2carbon";
	private String identityProviderMetadataPath = "resource:is-metadata.xml";
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			System.out.println(":::::" + beanNames.length + " beans");
		};
	}
		
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	@Bean
    public AuditorAware<String> auditorAware() {
		System.out.println("auditorAware");
        return new AuditorAwareImpl();
    }
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		System.out.println("propertySourcesPlaceholderConfigurer");
        return new PropertySourcesPlaceholderConfigurer();
    }

	@Bean
    public Config samlconfig() {
		System.out.println("SpringSecurityConfig config");
		final SAML2ClientConfiguration samlCfg = new SAML2ClientConfiguration();
		samlCfg.setKeystorePath(keystorePath);
		samlCfg.setKeystorePassword(keystorePassword);
		samlCfg.setPrivateKeyPassword(privateKeyPassword);	
		samlCfg.setIdentityProviderMetadataPath(identityProviderMetadataPath);	
		samlCfg.setMaximumAuthenticationLifetime(3600);
		samlCfg.setServiceProviderEntityId("cmon_app");
		samlCfg.setServiceProviderMetadataPath("sp-metadata.xml");
	    final SAML2Client saml2Client = new SAML2Client(samlCfg);
	    final Clients clients = new Clients("http://localhost:8082/cmon-web/callback", saml2Client);
	    final Config samlConfig = new Config(clients);
		return samlConfig;
	}
	
	
	//@Bean
	public FilterRegistrationBean cacheFilter() {
		FilterRegistrationBean rs = new FilterRegistrationBean(new CacheFilter());
		rs.addUrlPatterns("*.css");
		rs.addUrlPatterns("*.js");
		rs.addUrlPatterns("*.wpd");
		rs.addUrlPatterns("*.wcs");
		rs.addUrlPatterns("*.jpg");
		rs.addUrlPatterns("*.jpeg");
		rs.addUrlPatterns("*.png");
		return rs;
	}
}


