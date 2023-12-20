package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.common.models.districts.Districts;
import dev.jaczerob.council.common.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.models.news.NewsPartial;
import dev.jaczerob.council.common.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.models.status.Status;
import net.dv8tion.jda.api.EmbedBuilder;
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

        switch (body) {
            case Status status -> this.broadcastStatus(status);
            case NewsPartial news -> this.broadcastNews(news);
            case ZonedFieldOffices fieldOffices -> this.broadcastFieldOffices(fieldOffices);
            case Districts districts -> this.broadcastDistricts(districts);
            case ReleaseNotes releaseNotes -> this.broadcastReleaseNotes(releaseNotes);
            case null -> log.error("Null Toontown object: {}", message);
            default -> log.error("Unknown Toontown object {}: {}", body.getClass().getSimpleName(), body);
        }
    }

    private void broadcastReleaseNotes(final ReleaseNotes releaseNotes) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Release Notes");
        embed.setDescription(String.format("(%s) %s", releaseNotes.slug(), releaseNotes.date()));

        final String[] notes = releaseNotes.body().substring(1).split("=");
        for (final String note : notes) {
            final String[] noteParts = note.split("\\*");

            final String noteTitle = noteParts[0];
            final StringBuilder noteBody = new StringBuilder();
            for (int i = 1; i < noteParts.length; i++) {
                noteBody.append(String.format("• %s\n", noteParts[i]));
            }

            embed.addField(noteTitle, noteBody.toString(), false);
        }

        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessageEmbeds(embed.build())
                .queue();
    }

    private void broadcastDistricts(final Districts districts) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Districts");

        districts.districts().forEach(district -> {
            final String districtName = district.name();
            final int districtPopulation = district.population();
            final String districtStatus = district.status();

            embed.addField(districtName, String.format("Population: %d\nStatus: %s", districtPopulation, districtStatus), true);
        });

        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessageEmbeds(embed.build())
                .queue();
    }

    private void broadcastFieldOffices(final ZonedFieldOffices fieldOffices) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Field Offices");

        fieldOffices.fieldOffices().forEach(fieldOffice -> {
            final String zone = fieldOffice.zone();
            final String difficulty = "⭐".repeat(fieldOffice.difficulty());
            final int annexes = fieldOffice.annexes();
            final boolean open = fieldOffice.open();

            embed.addField(zone, String.format("Difficulty: %s\nAnnexes: %d\nOpen: %s", difficulty, annexes, open), true);
        });

        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessageEmbeds(embed.build())
                .queue();
    }

    private void broadcastStatus(final Status status) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Toontown Rewritten Status");
        embed.setDescription(String.format("Toontown Rewritten is: %s", status.open() ? "Open" : "Closed"));

        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessageEmbeds(embed.build())
                .queue();
    }

    private void broadcastNews(final NewsPartial news) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Toontown Rewritten News");
        embed.setDescription(String.format("%s - %s", news.author(), news.title()));
        embed.setUrl(news.url());

        this.jda.getTextChannelsByName("general", true).get(0)
                .sendMessageEmbeds(embed.build())
                .queue();
    }
}
