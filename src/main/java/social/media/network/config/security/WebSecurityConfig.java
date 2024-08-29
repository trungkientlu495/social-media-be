package social.media.network.config.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import social.media.network.config.security.jwt.JwtTokenFilter;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                requests -> requests
                                .requestMatchers("/api/v1/profile/**").hasAuthority("USER")
                                .requestMatchers("/api/v1/auth/account/logout").hasAuthority("USER")
                                .requestMatchers("/api/v1/auth/account/change-password").hasAuthority("USER")
                                .requestMatchers("/api/v1/auth/account/delete").hasAuthority("USER")
                                .requestMatchers("/api/v1/profile/**").hasAuthority("USER")
                                .requestMatchers("/api/v1/profile/update/**").hasAuthority("USER")
                                .requestMatchers("/api/v1/friend/**").hasAuthority("USER")
                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
       .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
