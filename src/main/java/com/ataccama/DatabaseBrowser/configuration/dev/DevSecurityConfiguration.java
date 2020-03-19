package com.ataccama.databasebrowser.configuration.dev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile(Profiles.DEV)
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("#{'${spring.security.excludedPaths}'.split(',')}")
    private String[] excludedPaths;

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(excludedPaths);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin()
                .and().logout()
                .and().csrf().ignoringAntMatchers("/h2-console/**") //because of embedded h2 console
                .and().headers().frameOptions().disable() //because of embedded h2 console
                .and().csrf().disable() //because of embedded h2 console
                .httpBasic();
    }

}
