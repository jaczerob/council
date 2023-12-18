package dev.jaczerob.council.common.models.status;

import dev.jaczerob.council.common.models.ToontownObject;

public record Status(
        boolean open,
        String status,
        String error
) implements ToontownObject {

}
