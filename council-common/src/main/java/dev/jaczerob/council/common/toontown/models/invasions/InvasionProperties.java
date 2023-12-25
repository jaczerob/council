package dev.jaczerob.council.common.toontown.models.invasions;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record InvasionProperties(
        int asOf,
        String type,
        String progress,
        String error
) implements ToontownObject {
}
