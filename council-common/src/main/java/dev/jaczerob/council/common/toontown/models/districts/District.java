package dev.jaczerob.council.common.toontown.models.districts;

import dev.jaczerob.council.common.toontown.models.ToontownObject;
import dev.jaczerob.council.common.toontown.models.invasions.InvasionProperties;

public record District(
        int population,
        String name,
        InvasionProperties invasion,
        String status
) implements ToontownObject {
    @Override
    public String error() {
        return "";
    }
}
