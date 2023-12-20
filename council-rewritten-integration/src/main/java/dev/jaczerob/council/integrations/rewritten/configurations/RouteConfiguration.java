package dev.jaczerob.council.integrations.rewritten.configurations;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    @Value("${broadcast.rate}")
    private long broadcastRate;

    @Override
    public void configure() {
        final Map<String, String> topicsToBroadcast = Map.of(
                this.newsTopic, "broadcastNews",
                this.statusTopic, "broadcastStatus",
                this.districtsTopic, "broadcastDistricts",
                this.releaseNotesTopic, "broadcastReleaseNotes",
                this.fieldOfficesTopic, "broadcastFieldOffices"
        );

        topicsToBroadcast.forEach((topic, method) ->
                from(String.format("timer://notifyEvents?fixedRate=true&period=%d", this.broadcastRate))
                    .to(String.format("bean:toontownUpdatesService?method=%s", method))
                    .choice()
                        .when(exchangeProperty("API_ERROR").isNotNull())
                            .log(String.format("API Error in %s: ${exchangeProperty.API_ERROR}", topic))
                        .endChoice()
                        .when(exchangeProperty("INTERNAL_ERROR").isNotNull())
                            .log(String.format("Internal Error in %s: ${exchangeProperty.INTERNAL_ERROR}", topic))
                        .endChoice()
                        .otherwise()
                            .log(String.format("Broadcasting to %s", topic))
                            .to(topic)
                        .endChoice()
                    .end()
        );
    }
}
