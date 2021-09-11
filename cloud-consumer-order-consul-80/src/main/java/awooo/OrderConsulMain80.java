package awooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@LoadBalancerClient(value = "cloud-provider-payment", configuration = RuleConfig.class)
@EnableDiscoveryClient
@SpringBootApplication
public class OrderConsulMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class, args);
    }

}
