package dev.jaczerob.council.common.models.districts;

import dev.jaczerob.council.common.models.ToontownObject;
import dev.jaczerob.council.common.models.invasions.InvasionProperties;

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
