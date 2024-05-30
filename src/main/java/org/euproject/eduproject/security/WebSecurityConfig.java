package org.euproject.eduproject.security;

import org.euproject.eduproject.util.constants.Privileges;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class WebSecurityConfig {
    
    private static final String[] WHITELIST = {
        "/",
        "/login",
        "/register",
        "/db-console/**",
        "/css/**",
        "/js/**",
        "/fonts/**",
        "/images/**"
    };


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests(
            authorize -> authorize
            .requestMatchers(WHITELIST).permitAll()
            .requestMatchers("/profile/**").authenticated()
            //todo fix hardcoding
            .requestMatchers("/moderator/**").hasAnyRole("ADMIN", "MODERATOR")
            .requestMatchers("/admin/**").hasAnyAuthority(Privileges.ACCESS_ADMIN_PANEL.getPrivilege())
            .requestMatchers("/test/**").hasAnyAuthority(Privileges.ACCESS_ADMIN_PANEL.getPrivilege())
            .anyRequest().authenticated())
            
            .formLogin(login -> login.loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error")
            .permitAll())
            
            .logout(logout -> logout.logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .permitAll())
            
            .rememberMe(remember -> remember.key("EuProjectKeySession"));
            
            //REMOVE FOR PROD -- ALERT ALERT REMOVE FOR PROD -- ALERT ALERT
            http.csrf().disable();
            http.headers().frameOptions().disable();
            
            return http.build();
        }
    
}
