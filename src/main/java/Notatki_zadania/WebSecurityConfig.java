package Notatki_zadania;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Notatki_zadania.Notatki_i_zadania.Szyfrowanie;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public SecretKey secretKey() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance("AES").generateKey();
    }

     @Bean
    public Szyfrowanie szyfrowanie(SecretKey secretKey) {
        return new Szyfrowanie(secretKey);
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