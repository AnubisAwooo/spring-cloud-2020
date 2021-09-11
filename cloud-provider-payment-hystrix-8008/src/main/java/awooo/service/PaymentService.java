package awooo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String paymentInfo(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(Long id) {
        long timeout = 5000;
//        try {
//            Thread.sleep(timeout);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int age = 10/0;
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~ timeout: " + timeout + "ms";
    }

    public String paymentInfoTimeoutHandle(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " " + id + " o(╥﹏╥)o";
    }

}
