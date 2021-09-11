package awooo.contoller;

import awooo.entities.Message;
import awooo.entities.Payment;
import awooo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public Message<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果 * " + result);
        if (result > 0) {
            return new Message<>(0, "success port: " + serverPort, result);
        } else {
            return new Message<>(444, "failed port: " + serverPort, null);
        }
    }

    @GetMapping("/get/{id}")
    public Message<?> get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果 " + payment);
        if (null != payment) {
            return new Message<>(0, "success port: " + serverPort, payment);
        } else {
            return new Message<>(444, "failed: " + id + " port: " + serverPort, null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> allKnownRegions = discoveryClient.getServices();
        for (String e : allKnownRegions) {
            log.info("server -> " + e);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance instance : instances) {
            log.info("service -> " + instance.getServiceId() + " " + instance.getInstanceId() + " " + instance.getHost());
        }

        return allKnownRegions;
    }

    @GetMapping("/feign/timeout")
    public Message<?> timeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Message<>(0, "success port: " + serverPort, null);
    }

}
