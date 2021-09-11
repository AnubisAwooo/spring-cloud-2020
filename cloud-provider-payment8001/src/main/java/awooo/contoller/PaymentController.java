package awooo.contoller;

import awooo.entities.Message;
import awooo.entities.Payment;
import awooo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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

}
