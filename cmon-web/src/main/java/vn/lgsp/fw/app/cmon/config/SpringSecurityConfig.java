package vn.lgsp.fw.app.cmon.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
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
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
          .authorizeRequests()//.anyRequest().permitAll();
          .antMatchers("/login*", "/zkau/**", "/backend/**").permitAll()
          
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login")
          .usernameParameter("username")
          .passwordParameter("password")
          .defaultSuccessUrl("/")
          .failureUrl("/login?error=true")
          .and()
          .logout().logoutSuccessUrl("/login");
    }
    
}
