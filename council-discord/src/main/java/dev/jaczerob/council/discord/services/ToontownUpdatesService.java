package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.common.toontown.models.districts.Districts;
import dev.jaczerob.council.common.toontown.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.toontown.models.news.NewsPartial;
import dev.jaczerob.council.common.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.toontown.models.status.Status;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToontownUpdatesService {
    private static final Logger log = LoggerFactory.getLogger(ToontownUpdatesService.class);

    private Status latestStatus = null;
    private NewsPartial latestNews = null;
    private ZonedFieldOffices latestFieldOffices = null;
    private Districts latestDistricts = null;
    private ReleaseNotes latestReleaseNotes = null;

    public Optional<Status> getStatus() {
        return Optional.ofNullable(this.latestStatus);
    }

    public Optional<NewsPartial> getNews() {
        return Optional.ofNullable(this.latestNews);
    }

    public Optional<ZonedFieldOffices> getFieldOffices() {
        return Optional.ofNullable(this.latestFieldOffices);
    }

    public Optional<Districts> getDistricts() {
        return Optional.ofNullable(this.latestDistricts);
    }

    public Optional<ReleaseNotes> getReleaseNotes() {
        return Optional.ofNullable(this.latestReleaseNotes);
    }

    public void updateLatestNews(final Exchange exchange) {
        final NewsPartial news = exchange.getMessage().getBody(NewsPartial.class);

        if (this.latestNews == null || !this.latestNews.equals(news)) {
            this.latestNews = news;
            log.info("Updated news: {}", news);
        }
    }

    public void updateLatestDistricts(final Exchange exchange) {
        final Districts districts = exchange.getMessage().getBody(Districts.class);

        if (this.latestDistricts == null || !this.latestDistricts.equals(districts)) {
            this.latestDistricts = districts;
            log.info("Updated districts: {}", districts);
        }
    }

    public void updateLatestReleaseNotes(final Exchange exchange) {
        final ReleaseNotes releaseNotes = exchange.getMessage().getBody(ReleaseNotes.class);

        if (this.latestReleaseNotes == null || !this.latestReleaseNotes.equals(releaseNotes)) {
            this.latestReleaseNotes = releaseNotes;
            log.info("Updated release notes: {}", releaseNotes);
        }
    }

    public void updateLatestFieldOffices(final Exchange exchange) {
        final ZonedFieldOffices fieldOffices = exchange.getMessage().getBody(ZonedFieldOffices.class);

        if (this.latestFieldOffices == null || !this.latestFieldOffices.equals(fieldOffices)) {
            this.latestFieldOffices = fieldOffices;
            log.info("Updated field offices: {}", fieldOffices);
        }
    }

    public void updateLatestStatus(final Exchange exchange) {
        final Status status = exchange.getMessage().getBody(Status.class);

        if (this.latestStatus == null || !this.latestStatus.equals(status)) {
            this.latestStatus = status;
            log.info("Updated status: {}", status);
        }
    }
}
