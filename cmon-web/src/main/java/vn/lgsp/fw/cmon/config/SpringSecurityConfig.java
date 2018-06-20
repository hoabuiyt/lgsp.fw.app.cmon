package vn.lgsp.fw.cmon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import vn.lgsp.fw.cmon.web.AppAuthenticationSuccessHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

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
    }
    
}
