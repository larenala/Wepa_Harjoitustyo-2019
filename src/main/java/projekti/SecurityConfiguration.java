
package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        // sallitaan framejen käyttö
        http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register","/register/**").permitAll()
                .antMatchers("/h2-console","/h2-console/**").permitAll()
                .antMatchers("/css", "/css/*").permitAll()
                .anyRequest().authenticated();
        
        http
                .formLogin().permitAll()
                .defaultSuccessUrl("/index",true)
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/register");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
