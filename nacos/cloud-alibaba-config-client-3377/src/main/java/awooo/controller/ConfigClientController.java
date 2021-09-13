package awooo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }

    @GetMapping("/config/info2")
    public String getConfigInfo2() {
        return "22222";
    }

    /**
     * 每次更新 nacos 配置，定时任务就不执行了，非得有请求过来，才会唤醒，毛病
     */
    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void print() {
        log.info("config.info -> {}", configInfo);
    }


}
