package dev.jaczerob.council.toonstats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jaczerob.council.common.toonstats.models.Toon;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ToonHQToon(
    int id,
    int game,
    String photo,
    int species,
    int laff,
    int toonup,
    int trap,
    int lure,
    int sound,

    @JsonProperty("throw")
    int throwGag,
    int squirt,
    int drop,
    List<String> prestiges,
    int sellbot,
    int cashbot,
    int lawbot,
    int bossbot
) {
    private static final Map<String, Integer> ORGANIC_LOOKUP = Map.of(
            "toonup", 1,
            "trap", 2,
            "lure", 3,
            "sound", 4,
            "throw", 5,
            "squirt", 6,
            "drop", 7
    );

    public Toon toToon() {
        final int organic;
        if (this.prestiges().isEmpty()) {
            organic = 0;
        } else {
            final String lastPrestige = this.prestiges().get(this.prestiges().size() - 1);
            organic = ORGANIC_LOOKUP.get(lastPrestige);
        }

        return new Toon(
            this.id(),
            this.species(),
            this.laff(),
            this.toonup(),
            this.trap(),
            this.lure(),
            this.sound(),
            this.throwGag(),
            this.squirt(),
            this.drop(),
            organic,
            this.sellbot(),
            this.cashbot(),
            this.lawbot(),
            this.bossbot()
        );
    }
}
