package awooo.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String paymentInfo(Long id) {
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~";
    }

    public String paymentInfoTimeout(Long id) {
        long timeout = 3000;
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " " + id + " O(∩_∩)O哈哈~ timeout: " + timeout + "ms";
    }

}
