package app.utils;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Config {
    private Dotenv env;

    @Value("${spring.profiles.active:prod}")
    private String activeProfile;

    private boolean isDev;

    @PostConstruct
    public void init(){
        isDev = "dev".equalsIgnoreCase(activeProfile);

        if (isDev){
            env = Dotenv.load();
            log.debug("Using dev environment loader");
        } else {
            log.debug("Using prod environment loader");
        }
    }

    public String get(String key){
        if (isDev && env != null){
            return env.get(key);
        } else {
            return System.getenv(key);
        }
    }

}
