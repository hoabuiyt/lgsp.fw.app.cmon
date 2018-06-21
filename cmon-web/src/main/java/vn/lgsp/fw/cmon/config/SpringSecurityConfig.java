package vn.lgsp.fw.cmon.config;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.pac4j.springframework.security.web.LogoutFilter;
import org.pac4j.springframework.security.web.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import vn.lgsp.fw.cmon.web.AppAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    Config config;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("admin").password("admin").roles("ADMIN");
    }
 
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
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/backend/**");
	    web.ignoring().antMatchers("/api/**");
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
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
          .logout().logoutSuccessUrl("/login");*/
    	System.out.println("securityyyyyyyyyyyyyyyy configure");
    	 final SecurityFilter filterLogin = new SecurityFilter(config, "Saml2Client");
         final LogoutFilter filterLogout = new LogoutFilter(config, "/");
         filterLogout.setDestroySession(true);

         http
                 .antMatcher("/**")
                 .authorizeRequests()
                     .antMatchers("/","/logout").permitAll()
                     .antMatchers("/**").authenticated()
                 .and()
                 .addFilterBefore(filterLogin, BasicAuthenticationFilter.class)
                 .addFilterBefore(filterLogout, BasicAuthenticationFilter.class)
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }
    
}
