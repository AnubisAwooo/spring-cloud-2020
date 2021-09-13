package awooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelServiceMain8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain8401.class, args);
    }

}
