package shopowner;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class securityconfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()                 // Links to WebConfig.java
            .and()
            .csrf().disable()       // Disable CSRF for Postman/Development
            .authorizeRequests()
            .anyRequest().permitAll(); // Allow all requests
    }
}