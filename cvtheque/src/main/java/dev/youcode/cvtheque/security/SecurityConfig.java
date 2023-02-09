package dev.youcode.cvtheque.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import dev.youcode.cvtheque.rule.Rule;
import dev.youcode.cvtheque.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private RsakeysConfig rsakeysConfig;
    private PasswordEncoder passwordEncoder;
    private final UserService userService;

    public SecurityConfig(RsakeysConfig rsakeysConfig, PasswordEncoder passwordEncoder, UserService userService) {
        this.rsakeysConfig = rsakeysConfig;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
@Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setPasswordEncoder(passwordEncoder);
    authProvider.setUserDetailsService(userDetailsService);
    return authProvider;
}
//    @Bean
//    public UserDetailsService inMemoryUserDetailsManager(){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder.encode("1234")).authorities("USER").build(),
//                User.withUsername("user2").password(passwordEncoder.encode("1234")).authorities("USER").build(),
//                User.withUsername("admin").password(passwordEncoder.encode("1234")).authorities("USER","ADMIN").build()
//        );
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeRequests(auth->auth.requestMatchers("/api/token/**").permitAll())
                .authorizeRequests(auth->auth.requestMatchers("/api/users/**").permitAll())
                .authorizeRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    }
    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk= new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email)  {
                dev.youcode.cvtheque.user.User user = null;
                List<SimpleGrantedAuthority> roles = new ArrayList<>();
                try {
                    user = userService.findUserByEmail(email);

                        roles.add(new SimpleGrantedAuthority(user.getRole()));

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return new User(user.getEmail(), user.getPassword(), roles);

            }
        };
    }

}