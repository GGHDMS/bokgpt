package kr.ac.bokgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BokgptApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokgptApplication.class, args);
    }

}
