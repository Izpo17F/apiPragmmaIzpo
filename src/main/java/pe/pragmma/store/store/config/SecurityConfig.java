package pe.pragmma.store.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pe.pragmma.store.store.filter.JwtGeneratorFilter;
import pe.pragmma.store.store.filter.JwtValidatorFilter;

@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtValidatorFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class);

        http
            .authorizeHttpRequests(requests ->
                requests.requestMatchers( "/swagger-ui/**").permitAll()
                    .requestMatchers( "/v3/api-docs*/**").permitAll()

                    .requestMatchers( HttpMethod.POST,"/user/**").permitAll()
                    .requestMatchers( "/user/**").authenticated()

                    .requestMatchers( HttpMethod.POST,"/product/**").hasRole("admin")
                    .requestMatchers( HttpMethod.PUT,"/product/**").hasRole("admin")
                    .requestMatchers( HttpMethod.DELETE,"/product/**").hasRole("admin")
                    .requestMatchers( "/product/*").authenticated()

                    .anyRequest().authenticated()
            );
            http.formLogin(AbstractHttpConfigurer::disable);
            http.httpBasic(Customizer.withDefaults());
            //http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
