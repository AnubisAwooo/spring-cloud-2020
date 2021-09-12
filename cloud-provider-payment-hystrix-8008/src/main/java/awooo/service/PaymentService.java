package awooo.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    public String paymentInfo(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~";
    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    public String paymentInfoTimeout(Long id) {
        long start = System.currentTimeMillis();
        long timeout = 5000;
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ignored) {
            log.error("thread sleep interrupted just sleep " + (System.currentTimeMillis() - start) + "ms");
        }
//        int age = 10/0;
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~ timeout: " + timeout + "ms";
    }

    public String paymentInfoTimeoutHandle(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " " + id + " o(╥﹏╥)o";
    }


    @HystrixCommand(fallbackMethod = "payCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String payCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id can not be negative");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + " success: " + serialNumber;
    }

    public String payCircuitBreakerFallback(Long id) {
        return Thread.currentThread().getName() + " to busy: " + id;
    }
}
