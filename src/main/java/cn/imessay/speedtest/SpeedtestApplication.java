package cn.imessay.speedtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpeedtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedtestApplication.class, args);
    }

}
