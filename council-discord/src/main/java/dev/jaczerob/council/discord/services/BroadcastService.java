package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.common.models.ToontownObject;
import dev.jaczerob.council.common.models.districts.Districts;
import dev.jaczerob.council.common.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.models.news.News;
import dev.jaczerob.council.common.models.news.NewsPartial;
import dev.jaczerob.council.common.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.models.status.Status;
import net.dv8tion.jda.api.JDA;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BroadcastService {
    private static final Logger log = LoggerFactory.getLogger(BroadcastService.class);

    private final JDA jda;

    public BroadcastService(final JDA jda) {
        this.jda = jda;
    }

    public void broadcast(final Exchange message) {
        final Object body = message.getMessage().getBody();
        log.info("Got Toontown object: {}", body);

        switch (body) {
            case Status status -> this.broadcastStatus(status);
            case NewsPartial news -> this.broadcastNews(news);
            case ZonedFieldOffices fieldOffices -> this.broadcastFieldOffices(fieldOffices);
            case Districts districts -> this.broadcastDistricts(districts);
            case ReleaseNotes releaseNotes -> this.broadcastReleaseNotes(releaseNotes);
            case null -> log.info("Null Toontown object: {}", message.getMessage().getBody());
            default -> log.info("Unknown Toontown object {}: {}", body.getClass().getSimpleName(), body);
        }
    }

    private void broadcastReleaseNotes(final ReleaseNotes releaseNotes) {
        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessage(String.format("Release Notes: %s", releaseNotes))
                .queue();
    }

    private void broadcastDistricts(final Districts districts) {
        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessage(String.format("Districts: %s", districts))
                .queue();
    }

    private void broadcastFieldOffices(final ZonedFieldOffices fieldOffices) {
        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessage(String.format("Field Offices: %s", fieldOffices))
                .queue();
    }

    private void broadcastStatus(final Status status) {
        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessage(String.format("Toontown Rewritten is: %s", status.open() ? "Open" : "Closed"))
                .queue();
    }

    private void broadcastNews(final NewsPartial news) {
        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessage(String.format("Toontown Rewritten News: %s", news.url()))
                .queue();
    }
}
