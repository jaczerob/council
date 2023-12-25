package dev.jaczerob.council.integrations.rewritten.services;

import dev.jaczerob.council.common.toontown.models.ToontownObject;
import dev.jaczerob.council.common.toontown.models.districts.Districts;
import dev.jaczerob.council.common.toontown.models.fieldoffices.FieldOffices;
import dev.jaczerob.council.common.toontown.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.toontown.models.invasions.Invasions;
import dev.jaczerob.council.common.toontown.models.news.News;
import dev.jaczerob.council.common.toontown.models.news.NewsPartial;
import dev.jaczerob.council.common.toontown.models.population.Population;
import dev.jaczerob.council.common.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.toontown.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.council.common.toontown.models.status.Status;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToontownUpdatesService {
    private static final Logger log = LoggerFactory.getLogger(ToontownUpdatesService.class);

    private final ToontownAPIService toontownAPIService;

    public ToontownUpdatesService(final ToontownAPIService toontownAPIService) {
        this.toontownAPIService = toontownAPIService;
    }

    public void broadcastStatus(final @NonNull Exchange exchange) {
        log.info("Broadcasting status");

        log.info("Getting status");
        final Status status = this.toontownAPIService.getStatus();
        final Optional<String> error = this.getError(status);
        if (error.isPresent()) {
            log.error("Got error getting Status: {}", error.get());
            exchange.setProperty("API_ERROR", error.get());
            return;
        }

        log.info("Got status: {}", status);

        exchange.getMessage().setBody(status);
        log.info("Broadcasted status");
    }

    public void broadcastReleaseNotes(final @NonNull Exchange exchange) {
        log.info("Broadcasting release notes");

        log.info("Getting release notes");
        final List<ReleaseNotesPartial> allReleaseNotes = this.toontownAPIService.getReleaseNotes();
        final Optional<String> allReleaseNotesError = this.getError(allReleaseNotes);
        if (allReleaseNotesError.isPresent()) {
            log.error("Got error getting release notes: {}", allReleaseNotesError.get());
            exchange.setProperty("API_ERROR", allReleaseNotesError.get());
            return;
        }

        final int latestReleaseNoteId = allReleaseNotes.get(0).noteId();
        final ReleaseNotes releaseNotes = this.toontownAPIService.getReleaseNotes(latestReleaseNoteId);
        final Optional<String> error = this.getError(allReleaseNotes);
        if (error.isPresent()) {
            log.error("Got error getting release notes: {}", error.get());
            exchange.setProperty("API_ERROR", error.get());
            return;
        }

        log.info("Got release notes: {}", releaseNotes);

        exchange.getMessage().setBody(releaseNotes);
        log.info("Broadcasted release notes");
    }

    public void broadcastDistricts(final @NonNull Exchange exchange) {
        log.info("Broadcasting districts");

        log.info("Getting population");
        final Population population = this.toontownAPIService.getPopulation();
        final Optional<String> populationError = this.getError(population);
        if (populationError.isPresent()) {
            log.error("Got error getting population: {}", populationError.get());
            exchange.setProperty("API_ERROR", populationError.get());
            return;
        }
        log.info("Got population");

        log.info("Getting invasions");
        final Invasions invasions = this.toontownAPIService.getInvasions();
        final Optional<String> invasionsError = this.getError(population);
        if (invasionsError.isPresent()) {
            log.error("Got error getting invasions: {}", invasionsError.get());
            exchange.setProperty("API_ERROR", invasionsError.get());
            return;
        }
        log.info("Got invasions");

        final Districts districts = Districts.fromInvasionsAndPopulation(invasions, population);
        log.info("Got districts");

        exchange.getMessage().setBody(districts);
        log.info("Broadcasted districts");
    }

    public void broadcastNews(final @NonNull Exchange exchange) {
        log.info("Broadcasting news");

        log.info("Getting news");
        final News news = this.toontownAPIService.getNews();
        final Optional<String> newsError = this.getError(news);
        if (newsError.isPresent()) {
            log.error("Got error getting news: {}", newsError.get());
            exchange.setProperty("API_ERROR", newsError.get());
            return;
        }

        final String newsUrl = String.format("https://toontownrewritten.com/news/item/%d/%s", news.postId(), news.title().toLowerCase().replaceAll(" ", "-"));
        final NewsPartial newsPartial = new NewsPartial(
                newsUrl,
                news.title(),
                news.author(),
                news.date(),
                news.image(),
                ""
        );
        log.info("Got news");

        exchange.getMessage().setBody(newsPartial);
        log.info("Broadcasted news");
    }

    public void broadcastFieldOffices(final @NonNull Exchange exchange) {
        log.info("Broadcasting field offices");

        log.info("Getting field offices");
        final FieldOffices fieldOffices = this.toontownAPIService.getFieldOffices();
        final Optional<String> fieldOfficesError = this.getError(fieldOffices);
        if (fieldOfficesError.isPresent()) {
            log.error("Got error getting news: {}", fieldOfficesError.get());
            exchange.setProperty("API_ERROR", fieldOfficesError.get());
            return;
        }

        final ZonedFieldOffices zonedFieldOffices = ZonedFieldOffices.fromFieldOffices(fieldOffices);
        log.info("Got field offices");

        exchange.getMessage().setBody(zonedFieldOffices);
        log.info("Broadcasted field offices");
    }

    private <T extends ToontownObject> Optional<String> getError(final T toontownObject) {
        if (toontownObject.error() != null && !toontownObject.error().isEmpty())
            return Optional.ofNullable(toontownObject.error());

        return Optional.empty();
    }

    private <T extends ToontownObject> Optional<String> getError(final List<T> toontownObjects) {
        for (final T toontownObject : toontownObjects)
            if (toontownObject.error() != null && !toontownObject.error().isEmpty())
                return Optional.ofNullable(toontownObject.error());

        return Optional.empty();
    }
}
