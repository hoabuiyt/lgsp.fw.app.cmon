package vn.lgsp.fw.cmon.config;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.security.web.CallbackFilter;
import org.pac4j.springframework.security.web.LogoutFilter;
import org.pac4j.springframework.security.web.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Value("${conf.application.baseUrl}")
	private static String baseUrl;
	
	//@formatter:off
	@Configuration
    @Order(1)
    public static class LogoutWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        protected void configure(final HttpSecurity http) throws Exception {
        	
        	 final LogoutFilter logoutFilter = new LogoutFilter(config, baseUrl);
             logoutFilter.setDestroySession(true);
             logoutFilter.setLocalLogout(false);
             logoutFilter.setCentralLogout(true);
             logoutFilter.setLogoutUrlPattern(baseUrl+"/.*");
        	 http
	             .antMatcher("/logout")
	             .addFilterBefore(logoutFilter, org.springframework.security.web.authentication.logout.LogoutFilter.class)
	             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        }
    }
	
	@Configuration
    @Order(2)
    public static class CallbackWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        protected void configure(final HttpSecurity http) throws Exception {
        	
        	  final CallbackFilter callbackFilter = new CallbackFilter(config, baseUrl);
              callbackFilter.setRenewSession(false);
              
              http.addFilterBefore(callbackFilter, BasicAuthenticationFilter.class);
              
              http
	      		.csrf().disable()
	    			.antMatcher("/callback")
	    			.authorizeRequests()
	    			.anyRequest().permitAll()
			        	.and()
			        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);  
              
        }
    }
	
	
	@Configuration
    @Order(3)
    public static class DefaultWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        @Override
    	public void configure(WebSecurity web) throws Exception {
    	    web.ignoring().antMatchers("/backend/**");
    	    web.ignoring().antMatchers("/api/**");
    	    web.ignoring().antMatchers("/error");
    	}
        
        protected void configure(final HttpSecurity http) throws Exception {

        	final SecurityFilter securityFilter = new SecurityFilter(config, "Saml2Client");
        	
            http.addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
            
            http
        		.csrf().disable()
        		.antMatcher("/").authorizeRequests().anyRequest().permitAll()
        			.and()
    			.antMatcher("/**").authorizeRequests().anyRequest().authenticated()
		        	.and()
	        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);  
        }
    }
	//@formatter:on
	
}
