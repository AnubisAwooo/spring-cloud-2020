package awooo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "testB";
    }

    @GetMapping("/testC")
    @SentinelResource(value = "testC", blockHandler = "testCFallback")
    public String testC() {
        return "testC";
    }

    public String testCFallback(BlockException e) {
        return "testCFallback";
    }

}
