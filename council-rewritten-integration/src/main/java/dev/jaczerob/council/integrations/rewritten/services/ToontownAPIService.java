package dev.jaczerob.council.integrations.rewritten.services;

import dev.jaczerob.council.common.models.fieldoffices.FieldOffices;
import dev.jaczerob.council.common.models.invasions.Invasions;
import dev.jaczerob.council.common.models.news.News;
import dev.jaczerob.council.common.models.population.Population;
import dev.jaczerob.council.common.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.council.common.models.status.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "toontown-api", url = "https://www.toontownrewritten.com/api")
public interface ToontownAPIService {
    @RequestMapping("/invasions")
    Invasions getInvasions();

    @RequestMapping("/population")
    Population getPopulation();

    @RequestMapping("/fieldoffices")
    FieldOffices getFieldOffices();

    @RequestMapping("/releasenotes")
    List<ReleaseNotesPartial> getReleaseNotes();

    @RequestMapping("/releasenotes/{id}")
    ReleaseNotes getReleaseNotes(@PathVariable("id") long id);

    @RequestMapping("/status")
    Status getStatus();

    @RequestMapping("/news")
    News getNews();
}
