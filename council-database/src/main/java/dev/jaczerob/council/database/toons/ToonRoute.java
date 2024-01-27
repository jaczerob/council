package dev.jaczerob.council.database.toons;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ToonRoute extends RouteBuilder {
    @Value("${topics.toons:activemq:topic:ToonTopic}")
    private String toonsTopic;

    @Override
    public void configure() {
        from(this.toonsTopic)
            .log("Received toons: ${body}")
            .to("bean:toonService?method=save");
    }
}
