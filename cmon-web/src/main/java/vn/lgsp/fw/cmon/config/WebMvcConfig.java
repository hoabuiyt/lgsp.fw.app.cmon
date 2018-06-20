package vn.lgsp.fw.cmon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	//@Bean
	public ViewResolver zulViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/zul/",  ".zul");
		resolver.setOrder(InternalResourceViewResolver.LOWEST_PRECEDENCE);
		return resolver;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/zkau/**","/backend/**")
            .addResourceLocations("/zkau/", "/backend/")
        ;
    }
}
