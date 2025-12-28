package employeeservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	//Cross-Origin Response Sharing
		    public void addCorsMappings(CorsRegistry registry) {
		        // Allow all origins or specify the frontend URL (e.g., http://localhost:4200)
		        registry.addMapping("/**")
		                .allowedOrigins("http://localhost:4200")  // Allow the frontend URL
		                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		                .allowedHeaders("*");
    }
}
