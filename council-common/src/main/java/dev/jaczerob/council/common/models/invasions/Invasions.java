package dev.jaczerob.council.common.models.invasions;

import dev.jaczerob.council.common.models.ToontownObject;

import java.util.Map;

public record Invasions(
        String error,
        int lastUpdated,
        Map<String, InvasionProperties> invasions
) implements ToontownObject {
}
