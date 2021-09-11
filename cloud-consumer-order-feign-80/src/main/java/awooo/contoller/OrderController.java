package awooo.contoller;

import awooo.entities.Message;
import awooo.entities.Payment;
import awooo.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
//    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Autowired
    private PaymentFeignService paymentFeignService;

    @PostMapping("/payment/create")
    public Message<?> create(@RequestBody Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/payment/get/{id}")
    public Message<?> getPayment(@PathVariable("id") Long id) {
        paymentFeignService.timeout();
        return paymentFeignService.get(id);
    }

}
