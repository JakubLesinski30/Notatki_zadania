package Notatki_zadania;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/js/**", "/css/**").permitAll()
                .requestMatchers("/", "/rejestracja", "/login", "/pomyslna_rejestracja").permitAll()
                .requestMatchers("/notatki", "/zadania").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("haslo")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutUrl("/wyloguj")
                .logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
        return http.build();
    }
}