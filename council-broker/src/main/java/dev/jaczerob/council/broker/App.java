package dev.jaczerob.council.broker;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(final String... args) {
        final ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(BrokerService.class).waitUntilStopped();
    }
}
