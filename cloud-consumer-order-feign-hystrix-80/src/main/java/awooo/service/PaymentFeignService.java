package awooo.service;

import awooo.entities.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
@FeignClient("cloud-provider-payment")
public interface PaymentFeignService {

    @GetMapping("/hystrix/{id}")
    Message<String> pay(@PathVariable("id") Long id);

    @GetMapping("/hystrix/timeout/{id}")
    Message<String> payTimeout(@PathVariable("id") Long id);

}
