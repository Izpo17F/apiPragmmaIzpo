package pe.pragmma.store.store.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.pragmma.store.store.exception.CustomAccessDeniedHandler;
import pe.pragmma.store.store.exception.CustomBasicAuthenticationEntryPoint;
import pe.pragmma.store.store.filter.JwtFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
            .authorizeHttpRequests(requests ->
                requests.requestMatchers( "/swagger-ui/**").permitAll()
                    .requestMatchers( "/v3/api-docs*/**").permitAll()

                    .requestMatchers( HttpMethod.POST,"/user/create").permitAll()
                    .requestMatchers( HttpMethod.GET,"/user/auth").permitAll()
                    .requestMatchers( "/user/**").authenticated()

                    .requestMatchers( HttpMethod.POST,"/product/**").hasRole("admin")
                    .requestMatchers( HttpMethod.PUT,"/product/**").hasRole("admin")
                    .requestMatchers( HttpMethod.DELETE,"/product/**").hasRole("admin")
                    .requestMatchers( "/product/**").authenticated()

                    .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("admin")

                    .anyRequest().authenticated()
            );
            http.formLogin(AbstractHttpConfigurer::disable);
           // http.httpBasic(Customizer.withDefaults());
            http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
            http.exceptionHandling(e -> e.accessDeniedHandler(new CustomAccessDeniedHandler()));


            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
