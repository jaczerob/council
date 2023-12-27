package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannel;
import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import dev.jaczerob.council.common.toontown.models.districts.Districts;
import dev.jaczerob.council.common.toontown.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.toontown.models.news.NewsPartial;
import dev.jaczerob.council.common.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.toontown.models.status.Status;
import dev.jaczerob.council.discord.utils.EmbedCreator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastService {
    private static final Logger log = LoggerFactory.getLogger(BroadcastService.class);

    private final JDA jda;
    private final BroadcastChannelService broadcastChannelService;

    private Status latestStatus = null;
    private NewsPartial latestNews = null;
    private ZonedFieldOffices latestFieldOffices = null;
    private Districts latestDistricts = null;
    private ReleaseNotes latestReleaseNotes = null;

    public BroadcastService(final JDA jda, final BroadcastChannelService broadcastChannelService) {
        this.jda = jda;
        this.broadcastChannelService = broadcastChannelService;
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

    public void broadcastLatestStatus(final GuildMessageChannel channel) {
        if (this.latestStatus == null) {
            log.error("No status to broadcast");
            return;
        }

        this.sendBroadcast(EmbedCreator.fromStatus(this.latestStatus), channel);
    }

    public void broadcastLatestNews(final GuildMessageChannel channel) {
        if (this.latestNews == null) {
            log.error("No news to broadcast");
            return;
        }

        this.sendBroadcast(EmbedCreator.fromNews(this.latestNews), channel);
    }

    public void broadcastLatestFieldOffices(final GuildMessageChannel channel) {
        if (this.latestFieldOffices == null) {
            log.error("No field offices to broadcast");
            return;
        }

        this.sendBroadcast(EmbedCreator.fromFieldOffices(this.latestFieldOffices), channel);
    }

    public void broadcastLatestDistricts(final GuildMessageChannel channel) {
        if (this.latestDistricts == null) {
            log.error("No districts to broadcast");
            return;
        }

        this.sendBroadcast(EmbedCreator.fromDistricts(this.latestDistricts), channel);
    }

    public void broadcastLatestReleaseNotes(final GuildMessageChannel channel) {
        if (this.latestReleaseNotes == null) {
            log.error("No release notes to broadcast");
            return;
        }

        this.sendBroadcast(EmbedCreator.fromReleaseNotes(this.latestReleaseNotes), channel);
    }

    private void broadcastReleaseNotes(final ReleaseNotes releaseNotes) {
        if (this.latestReleaseNotes == null) {
            this.latestReleaseNotes = releaseNotes;
        } else {
            if (this.latestReleaseNotes.equals(releaseNotes)) {
                log.info("Release notes have not changed");
                return;
            }
        }

        this.sendBroadcasts(BroadcastChannelType.RELEASENOTES, EmbedCreator.fromReleaseNotes(releaseNotes));
    }

    private void broadcastDistricts(final Districts districts) {
        if (this.latestDistricts == null) {
            this.latestDistricts = districts;
        } else {
            if (this.latestDistricts.equals(districts)) {
                log.info("Districts have not changed");
                return;
            }
        }

        this.sendBroadcasts(BroadcastChannelType.DISTRICTS, EmbedCreator.fromDistricts(districts));
    }

    private void broadcastFieldOffices(final ZonedFieldOffices fieldOffices) {
        if (this.latestFieldOffices == null) {
            this.latestFieldOffices = fieldOffices;
        } else {
            if (this.latestFieldOffices.equals(fieldOffices)) {
                log.info("Field offices have not changed");
                return;
            }
        }

        this.sendBroadcasts(BroadcastChannelType.FIELDOFFICES, EmbedCreator.fromFieldOffices(fieldOffices));
    }

    private void broadcastStatus(final Status status) {
        if (this.latestStatus == null) {
            this.latestStatus = status;
        } else {
            if (this.latestStatus.equals(status)) {
                log.info("Status has not changed");
                return;
            }
        }

        this.sendBroadcasts(BroadcastChannelType.STATUS, EmbedCreator.fromStatus(status));
    }

    private void broadcastNews(final NewsPartial news) {
        if (this.latestNews == null) {
            this.latestNews = news;
        } else {
            if (this.latestNews.equals(news)) {
                log.info("News has not changed");
                return;
            }
        }

        this.sendBroadcasts(BroadcastChannelType.NEWS, EmbedCreator.fromNews(news));
    }

    private void sendBroadcast(final MessageEmbed embed, final GuildMessageChannel channel) {
        channel.sendMessageEmbeds(embed).queue();
        log.info("Sent broadcast to channel {}", channel.getName());
    }

    private void sendBroadcasts(final BroadcastChannelType type, final MessageEmbed embed) {
        final List<BroadcastChannel> broadcastChannels = this.broadcastChannelService.getBroadcastChannels(type);

        if (broadcastChannels == null) {
            log.error("Could not retrieve broadcast channels");
            return;
        }

        broadcastChannels.forEach(broadcastChannel -> {
            final TextChannel channel = this.jda.getTextChannelById(broadcastChannel.id());
            if (channel == null) {
                log.error("Could not find channel {}", broadcastChannel.id());
                return;
            }

            this.sendBroadcast(embed, channel);
        });
    }
}
