package alura.sportpot.infrastructure.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserService userService;

  @Autowired
  private TokenService tokenService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);

    auth.userDetailsService(userService)
      .passwordEncoder(new BCryptPasswordEncoder());
  }  

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers(
        "/login/**",
        "/swagger-resources/**",
        "/v2/api-docs",
        "/configuration/ui",
        "/configuration/security",
        "/webjars/**",
        "/swagger-ui.html"
      )
      .permitAll()
      .antMatchers(HttpMethod.POST, "/login/user")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .addFilterBefore(new JwtAuthenticationFilter(this.tokenService, this.userService), UsernamePasswordAuthenticationFilter.class)
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}