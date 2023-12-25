package dev.jaczerob.council.common.toontown.models.status;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record Status(
        boolean open,
        String status,
        String error
) implements ToontownObject {

}
