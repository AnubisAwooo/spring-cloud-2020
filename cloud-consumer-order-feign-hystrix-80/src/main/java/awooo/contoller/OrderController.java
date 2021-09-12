package awooo.contoller;

import awooo.entities.Message;
import awooo.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@DefaultProperties(defaultFallback = "payTimeoutHandleGlobal")
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
//    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Autowired
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/{id}")
    public Message<?> pay(@PathVariable("id") Long id) {
        String result = paymentFeignService.pay(id).getData();
        return new Message<>(0, "success port: " + serverPort, result);
    }

    @GetMapping("/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "payTimeoutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public Message<?> payTimeout(@PathVariable("id") Long id) {
        String result = paymentFeignService.payTimeout(id).getData();
        return new Message<>(0, "success port: " + serverPort, result);
    }

    public Message<?> payTimeoutHandle(Long id) {
        String result = "80 超时 " + id;
        return new Message<>(0, "success port: " + serverPort, result);
    }

    public Message<?> payTimeoutHandleGlobal() {
        String result = "80 全局超时 ";
        return new Message<>(0, "success port: " + serverPort, result);
    }

}
