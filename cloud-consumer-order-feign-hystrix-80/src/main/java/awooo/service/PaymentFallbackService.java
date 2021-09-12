package awooo.service;

import awooo.entities.Message;


public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public Message<String> pay(Long id) {
        return new Message<>(0, "PaymentFallbackService pay", "PaymentFallbackService pay");
    }

    @Override
    public Message<String> payTimeout(Long id) {
        return new Message<>(0, "PaymentFallbackService payTimeout", "PaymentFallbackService payTimeout");
    }
}
