package dev.jaczerob.council.toonstats.configurations;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RouteConfiguration extends RouteBuilder {
    @Value("${topics.toon:activemq:topic:ToonTopic}")
    private String toonTopic;

    @Value("${broadcast.rate:60000}")
    private long broadcastRate;

    @Override
    public void configure() {
            from(String.format("timer://notifyEvents?fixedRate=true&period=%d", this.broadcastRate))
                    .to("bean:toonScrapingService?method=findToons")
                    .log("Publishing toons to %s".formatted(this.toonTopic))
                    .to(this.toonTopic)
                    .end();
    }
}