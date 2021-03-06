package com.example.graduation_work.config;


import com.example.graduation_work.service.impl.auth.AuthTokenFilter;
import com.example.graduation_work.service.impl.auth.AuthenticationEntryPointImp;
import com.example.graduation_work.service.impl.auth.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImp userDetailsService;
    private AuthenticationEntryPointImp authenticationEntryPoint;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance(); // TODO Сделать шифрование паролей
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable() // TODO Включить и настроить CSRF
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests()
                    .antMatchers(
                        "/v2/api-docs",
                        "/api/testing/hello",
                        "/schedule",
                        "/teacher/**",
                        "/schedule/list",
                        "/study-group/list"
                        ,"/**"
                    ).permitAll()
                    .antMatchers("/login").not().fullyAuthenticated()
                    .anyRequest().authenticated();
        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPointImp authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
}
