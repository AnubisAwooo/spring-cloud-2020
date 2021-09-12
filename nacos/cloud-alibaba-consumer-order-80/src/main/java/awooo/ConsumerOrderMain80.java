package awooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain80.class, args);
    }

}
