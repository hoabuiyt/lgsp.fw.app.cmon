package vn.lgsp.fw.cmon.config;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.pac4j.springframework.security.web.CallbackFilter;
import org.pac4j.springframework.security.web.LogoutFilter;
import org.pac4j.springframework.security.web.Pac4jEntryPoint;
import org.pac4j.springframework.security.web.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import vn.lgsp.fw.cmon.web.AppAuthenticationSuccessHandler;

//@EnableWebSecurity
public class SpringSecurityConfig {
	
	//@Configuration
    //@Order(2)
    public static class Saml2WebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        @Override
    	public void configure(WebSecurity web) throws Exception {
    	    web.ignoring().antMatchers("/backend/**");
    	    web.ignoring().antMatchers("/api/**");
    	    web.ignoring().antMatchers("/error");
    	    //web.ignoring().antMatchers("/callback");
    	}
        
        /*protected void configure(final HttpSecurity http) throws Exception {

            final SecurityFilter filter = new SecurityFilter(config, "Saml2Client");

            http .headers().httpStrictTransportSecurity().disable()
		         .and().csrf().disable()
                    .antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and().addFilterBefore(filter, BasicAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        }*/
    }
	
	//@Configuration
    @Order(1)
    public static class DefaultWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private Config config;

        @Override
    	public void configure(WebSecurity web) throws Exception {
    	    web.ignoring().antMatchers("/backend/**");
    	    web.ignoring().antMatchers("/api/**");
    	    web.ignoring().antMatchers("/error");
    	    //web.ignoring().antMatchers("/callback");
    	}
        
        protected void configure(final HttpSecurity http) throws Exception {

        	final SecurityFilter filter = new SecurityFilter(config, "Saml2Client");
        	
            final CallbackFilter callbackFilter = new CallbackFilter(config, "/cmon-web/");
            callbackFilter.setRenewSession(false);
            //callbackFilter.setMultiProfile(true);
            http.addFilterBefore(callbackFilter, BasicAuthenticationFilter.class);
            http.addFilterBefore(filter, BasicAuthenticationFilter.class);
            
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            	.and()
            		.csrf().disable()
        			//.antMatcher("/**")
        			.authorizeRequests()
        			.anyRequest().permitAll();
		       /* .and()
		        	.exceptionHandling().authenticationEntryPoint(new Pac4jEntryPoint(config, "SAML2Client"))
		        .and()
			        .logout().logoutSuccessUrl("/"); */ 
        }
    }
	
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin").password("admin").roles("ADMIN");
    }*/
 
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("admin","admin, ADMIN, enabled"); //add whatever other user you need
        return new InMemoryUserDetailsManager(users);
    }
    */
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/backend/**");
	    web.ignoring().antMatchers("/api/**");
	    web.ignoring().antMatchers("/error");
	    //web.ignoring().antMatchers("/callback");
	}*/
	
	
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
          .authorizeRequests()//.anyRequest().permitAll();
          .antMatchers("/login*","/zkau/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login")
          .usernameParameter("username")
          .passwordParameter("password")
          .successHandler(new AppAuthenticationSuccessHandler())
          .and()
          .logout().logoutSuccessUrl("/login");
    	System.out.println("securityyyyyyyyyyyyyyyy configure");
    	 final SecurityFilter filterLogin = new SecurityFilter(config, "Saml2Client");
         //final LogoutFilter filterLogout = new LogoutFilter(config, "/");
         //filterLogout.setDestroySession(true);
    	 final CallbackFilter callbackFilter = new CallbackFilter(config);
         http.csrf().disable()
         .headers()
         .httpStrictTransportSecurity().disable().and()
                 .antMatcher("/**")
                 .authorizeRequests()
                     //.antMatchers("/callback*").permitAll()
                     .anyRequest().authenticated()
                 //.and()
                 //.exceptionHandling().authenticationEntryPoint(new Pac4jEntryPoint(config, "Saml2Client"))
                 .and()
                 .addFilterBefore(filterLogin, BasicAuthenticationFilter.class)
                 .addFilterBefore(callbackFilter, BasicAuthenticationFilter.class)
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }*/
    
}
