package dev.jaczerob.council.toonstats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jaczerob.council.common.toonstats.models.Toon;
import dev.jaczerob.council.common.toonstats.models.Toons;
import dev.jaczerob.council.toonstats.models.ToonHQToon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ToonScrapingService {
    private static final Logger log = LoggerFactory.getLogger(ToonScrapingService.class);

    private static final Pattern TOON_PATTERN = Pattern.compile("\"toon\": (.+?})", Pattern.DOTALL);
    private static final String TOON_HQ_URL = "https://toonhq.org/groups";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Toons findToons() {
        final Document document;

        try {
            document = Jsoup.connect(TOON_HQ_URL).get();
        } catch (final IOException exc) {
            log.error("Could not connect to ToonHQ at: {}", TOON_HQ_URL, exc);
            return new Toons(List.of());
        }

        final Matcher toonMatcher = TOON_PATTERN.matcher(document.html());

        final List<Toon> toons = new ArrayList<>();

        while (toonMatcher.find()) {
            final String toonJson = toonMatcher.group(1);
            final ToonHQToon toonHQToon;

            try {
                toonHQToon = OBJECT_MAPPER.readValue(toonJson, ToonHQToon.class);
            } catch (final JsonProcessingException exc) {
                log.error("Could not parse ToonHQ Toon: {}", toonJson, exc);
                continue;
            }

            if (toonHQToon.id() == 0) {
                log.warn("ToonHQ Toon has no ID: {}", toonJson);
                continue;
            } else if (toonHQToon.photo() == null) {
                log.warn("ToonHQ Toon has no photo, potentially unsynced: {}", toonJson);
                continue;
            } else if (toonHQToon.game() != 1) {
                log.warn("ToonHQ Toon is not a TTR toon: {}", toonJson);
                continue;
            }

            toons.add(toonHQToon.toToon());
        }

        return new Toons(toons);
    }
}
