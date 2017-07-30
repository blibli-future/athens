package com.blibli.future.config;


import com.blibli.future.security.JwtAuthenticationEntryPoint;
import com.blibli.future.security.JwtAuthenticationFilter;
import com.blibli.future.security.JwtAuthenticationProvider;
import com.blibli.future.security.JwtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    public JwtAuthenticationFilter authenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/shift").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        http.addFilterBefore(this.authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**");
    }
}