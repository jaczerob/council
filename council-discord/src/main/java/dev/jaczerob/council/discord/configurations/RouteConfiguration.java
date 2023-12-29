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
        from(this.newsTopic).to("bean:toontownUpdatesService?method=updateLatestNews").end();
        from(this.statusTopic).to("bean:toontownUpdatesService?method=updateLatestStatus").end();
        from(this.districtsTopic).to("bean:toontownUpdatesService?method=updateLatestDistricts").end();
        from(this.releaseNotesTopic).to("bean:toontownUpdatesService?method=updateLatestReleaseNotes").end();
        from(this.fieldOfficesTopic).to("bean:toontownUpdatesService?method=updateLatestFieldOffices").end();
    }
}
