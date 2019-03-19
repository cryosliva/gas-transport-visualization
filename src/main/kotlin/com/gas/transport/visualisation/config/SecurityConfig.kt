package com.gas.transport.visualisation.config

import com.gas.transport.visualisation.security.UsernamePasswordAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var usernamePasswordAuthenticationProvider: UsernamePasswordAuthenticationProvider

    @Autowired
    private lateinit var authEntryPoint: AuthenticationEntryPoint


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/register*").anonymous()
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                .and().csrf().disable()
    }

    @Bean
    fun encoder() =  BCryptPasswordEncoder()

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
            }

        }
    }
}