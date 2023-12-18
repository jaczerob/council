package dev.jaczerob.council.discord.configurations;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteConfiguration extends RouteBuilder {
    @Value("${topics.news}")
    private String newsTopic;

    @Value("${topics.status}")
    private String statusTopic;

    @Value("${topics.districts}")
    private String districtsTopic;

    @Value("${topics.release-notes}")
    private String releaseNotesTopic;

    @Value("${topics.field-offices}")
    private String fieldOfficesTopic;

    @Override
    public void configure() throws Exception {
        from(this.newsTopic).to("bean:broadcastService?method=broadcast").end();
        from(this.statusTopic).to("bean:broadcastService?method=broadcast").end();
        from(this.districtsTopic).to("bean:broadcastService?method=broadcast").end();
        from(this.releaseNotesTopic).to("bean:broadcastService?method=broadcast").end();
        from(this.fieldOfficesTopic).to("bean:broadcastService?method=broadcast").end();
    }
}
