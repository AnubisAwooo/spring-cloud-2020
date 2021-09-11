package awooo.service;

import awooo.entities.Message;
import awooo.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/payment")
@FeignClient("cloud-provider-payment")
public interface PaymentFeignService {

    @PostMapping("/create")
    Message<?> create(@RequestBody Payment payment);

    @GetMapping("/get/{id}")
    Message<?> get(@PathVariable("id") Long id);

    @GetMapping("/feign/timeout")
    Message<?> timeout();

}
