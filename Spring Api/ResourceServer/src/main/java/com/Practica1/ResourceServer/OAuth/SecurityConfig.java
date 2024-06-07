    package com.Practica1.ResourceServer.OAuth;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.GET,"/api/users/**").hasAuthority("SCOPE_write")
                            .requestMatchers(HttpMethod.POST,"/api/users/**").hasAuthority("SCOPE_write")
                            .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasAuthority("SCOPE_write")
                            .requestMatchers(HttpMethod.GET,"api/pokemon/**").hasAuthority("SCOPE_write")
                            .requestMatchers(HttpMethod.GET,"api/cypher/**").hasAuthority("SCOPE_write")
                            .requestMatchers("/h2-ui/**").permitAll()
                    )
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                    .csrf().ignoringRequestMatchers("/h2-ui/**"); // Ignorar la protección CSRF para la interfaz H2
                    // Deshabilitar protección CSRF y X-Frame-Options para la consola H2
                    http.csrf().disable();
                    http.headers().frameOptions().disable();


            return http.build();
        }
    }
