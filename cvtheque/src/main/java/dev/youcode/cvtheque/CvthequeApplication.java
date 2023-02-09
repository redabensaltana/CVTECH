package dev.youcode.cvtheque;


import dev.youcode.cvtheque.security.RsakeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)

@ComponentScan(basePackages = {"dev.youcode.cvtheque.comment","dev.youcode.cvtheque.security","dev.youcode.cvtheque.user","dev.youcode.cvtheque.experience","dev.youcode.cvtheque.skill","dev.youcode.cvtheque.language","dev.youcode.cvtheque.rule","dev.youcode.cvtheque.web","dev.youcode.cvtheque.util", "dev.youcode.cvtheque.resume", "dev.youcode.cvtheque.education", "dev.youcode.cvtheque.project"})
@Configuration

public class CvthequeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CvthequeApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}



