package brblnt.icms.config;

import java.util.concurrent.TimeUnit;

import brblnt.icms.web.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security configuration with Spring Security.
 * Require auth from every request. Allow remember me function with cookies.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  @Autowired
  private LoginSuccessHandler loginSuccessHandler;


  /**
   * login section optional: .failureUrl("/login/failed").
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/css/*", "/js/*").permitAll()
            .antMatchers("/login/help").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .passwordParameter("loginInputPassword")
            .usernameParameter("loginInputUsername")
            .successHandler(loginSuccessHandler)
            .and()
            .rememberMe()
            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
            .key("IOhKEOQHzkeZefjQzgRHKPMKBEeToCnl")
            .rememberMeParameter("loginRememberMe")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "remember-me")
            .logoutSuccessUrl("/login?logout");
    http.headers().frameOptions().sameOrigin();
    log.info("Setup Security filter chain.");
    return http.build();
  }


}
