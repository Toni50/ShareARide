package com.alektoni.sharearide.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtRequestsFilter jwtRequestsFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

     /*   auth.jdbcAuthentication()
        .dataSource(dataSource)
              .usersByUsernameQuery("select username,password,enabled form users where username = ?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");*/

     auth.userDetailsService(myUserDetailsService);
    }



     @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/sharearide/rest/authentication/authenticate").permitAll()
                .antMatchers("/sharearide/rest/user/register").permitAll()
                .anyRequest().authenticated().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

         http.addFilterBefore(jwtRequestsFilter, UsernamePasswordAuthenticationFilter.class);
    }



   /*
   Override method authenticationManagerBean in WebSecurityConfigurerAdapter to
   expose the AuthenticationManager built using configure(AuthenticationManagerBuilder) as a Spring bean:
    */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }//todo make custom encoder

    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
}
