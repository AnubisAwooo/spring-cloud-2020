package awooo.service;

import awooo.entities.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cloud-provider-payment", path = "/payment", fallbackFactory = PaymentFeignFactory.class)
public interface PaymentFeignService {

    @RequestMapping(value = "/hystrix/{id}", method = RequestMethod.GET)
    Message<String> pay(@PathVariable("id") Long id);

    @RequestMapping(value = "/hystrix/timeout/{id}",method = RequestMethod.GET)
    Message<String> payTimeout(@PathVariable("id") Long id);

}
