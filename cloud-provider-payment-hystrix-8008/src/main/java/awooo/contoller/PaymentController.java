package awooo.contoller;

import awooo.entities.Message;
import awooo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/{id}")
    public Message<?> pay(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfo(id);
        return new Message<>(0, "success port: " + serverPort, result);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public Message<?> payTimeout(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfoTimeout(id);
        return new Message<>(0, "success port: " + serverPort, result);
    }

}
