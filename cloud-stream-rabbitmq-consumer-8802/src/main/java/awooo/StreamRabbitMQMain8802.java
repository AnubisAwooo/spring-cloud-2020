package awooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StreamRabbitMQMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQMain8802.class, args);
    }

}
