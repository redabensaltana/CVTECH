package dev.youcode.cvtheque.web;


import dev.youcode.cvtheque.response.AuthResponse;
import dev.youcode.cvtheque.user.UserDTO;
import dev.youcode.cvtheque.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserService userService;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/api/token")
    public AuthResponse jwtToken(@RequestBody UserDTO userDTO) {
        System.out.println("test jwt token");
        String subject=null;
        String scope=null;

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
            subject=authentication.getName();

            scope=authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));
            Instant instant=Instant.now();
            JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .claim("scope",scope)
                    .build();
            String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
            Long id = userService.findUserByEmail(subject).getUserId();

            if(!userService.findUserByEmail(subject).getUserTitle().equals("CME")) {
                Long resume_id = userService.findUserByEmail(subject).getUserResumeId().getResumeId();
                return new AuthResponse("Authentication succeeded!", jwtAccessToken, id, resume_id);
            }
            return new AuthResponse("Authentication succeeded!", jwtAccessToken, id);


        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
        }

           return new AuthResponse("Authentication failed!");
    }
}