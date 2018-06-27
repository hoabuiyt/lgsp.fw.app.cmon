package vn.lgsp.fw.cmon;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.opensaml.saml.common.xml.SAMLConstants;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.pac4j.springframework.security.authentication.Pac4jAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.core.context.SecurityContextHolder;

import vn.lgsp.fw.cmon.config.SAML2Properties;
import vn.lgsp.fw.cmon.domain.AuditorAwareImpl;
import vn.lgsp.fw.cmon.web.CacheFilter;
import vn.lgsp.fw.core.BaseRepositoryImpl;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@ComponentScan(basePackages = { "vn.lgsp.fw", "org.pac4j.springframework.web"  })
public class Application extends SpringBootServletInitializer{
	
	@Autowired
	SAML2Properties saml2Properties;
	
	@Value("${conf.application.baseUrl}")
	private String baseUrl;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//@Bean
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
        return new AuditorAwareImpl();
    }
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	@Bean
    public Config samlconfig() {
		final SAML2ClientConfiguration samlCfg = new SAML2ClientConfiguration();
		samlCfg.setKeystorePath(saml2Properties.getKeystorePath());
		samlCfg.setKeystorePassword(saml2Properties.getKeystorePassword());
		samlCfg.setKeystoreAlias(saml2Properties.getKeystoreAlias());
		samlCfg.setPrivateKeyPassword(saml2Properties.getPrivateKeyPassword());	
		samlCfg.setServiceProviderEntityId(saml2Properties.getServiceProviderEntityId());
		samlCfg.setIdentityProviderMetadataPath(saml2Properties.getIdentityProviderMetadataPath());
		samlCfg.setServiceProviderMetadataPath("sp-metadata.xml");
		samlCfg.setMaximumAuthenticationLifetime(3600);
		samlCfg.setDestinationBindingType(SAMLConstants.SAML2_REDIRECT_BINDING_URI);
	    final SAML2Client saml2Client = new SAML2Client(samlCfg);
	    //saml2Client.setCredentialsExtractor(new SAML2CredentialsExtractor(samlCfg, saml2Client));
	    saml2Client.addAuthorizationGenerator((ctx, profile) -> {
	        if (profile.getAttribute("roles") != null) {
	            List<String> roles = (List) profile.getAttribute("roles");
	           /* Set<String> filteredGroups = roles.stream()
	                    .filter(group -> group.startsWith("ADMIN_"))
	                    .collect(Collectors.toSet());*/
	            profile.addRoles(roles);
	            LinkedHashMap<String, CommonProfile> samlProfile = new LinkedHashMap<>();
	            samlProfile.put("samlProfile", profile);
	            SecurityContextHolder.getContext().setAuthentication(new Pac4jAuthenticationToken(samlProfile));
	        }
	        
	        return profile;
	    });
	    final Clients clients = new Clients(baseUrl+"/callback", saml2Client);
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


