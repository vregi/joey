package app;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class Joey {
    public static void main(String[] args) {
        SpringApplication.run(Joey.class, args);
    }
}