package dev.jaczerob.council.common.models.invasions;

import dev.jaczerob.council.common.models.ToontownObject;

public record InvasionProperties(
        int asOf,
        String type,
        String progress,
        String error
) implements ToontownObject {
}
