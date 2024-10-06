package com.springweb.SpringBootSecurityMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author Vu Duy Linh
 * References:
 *  - https://www.javainuse.com/webseries/spring-security-jwt/chap3
 *  - https://www.bezkoder.com/websecurityconfigureadapter-deprecated-spring-boot/
 * */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    // @Autowired private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    // encoder pwd to inject into UserEntityServiceImpl
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean //can be omitted
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean //can be omitted
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsServiceImpl);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    /*
     * https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html
     * "In Spring Security 6, the default is that authentication mechanisms themselves
     * must invoke the SessionAuthenticationStrategy."
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( (authorizeRequests) -> authorizeRequests
                .requestMatchers("/", "/home", "/login", "/403", "/css/**", "/images/**").permitAll()
                .requestMatchers("/registration", "/webjars/**").permitAll()

                // Nếu muốn cho khách vãng lai (chưa đăng nhập) xem danh sách các sản phẩm
                .requestMatchers("/products").permitAll()

                .anyRequest().authenticated()
        )
        .formLogin((formBased) -> {
            formBased
//                .usernameParameter("email")  --> NEED <input type="email" name="email">
//                .passwordParameter("password")
                .loginPage("/login")
                .defaultSuccessUrl("/products").permitAll()
                .failureUrl("/login?error=true").permitAll();
        	}
        )
        .logout((logout) -> {
            logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        	}
        )
        .exceptionHandling(except -> except
                .accessDeniedHandler(customAccessDeniedHandler)
                .accessDeniedPage("/403")
        );

        // http.authenticationProvider(authenticationProvider()); //can be omitted

        return http.build();
    }
	
}
