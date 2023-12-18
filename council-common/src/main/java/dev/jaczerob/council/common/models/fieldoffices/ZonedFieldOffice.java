package dev.jaczerob.council.common.models.fieldoffices;

import dev.jaczerob.council.common.models.ToontownObject;

public record ZonedFieldOffice(
        String department,
        int difficulty,
        int annexes,
        boolean open,
        int expiring,
        String zone
) implements ToontownObject {
    @Override
    public String error() {
        return "";
    }
}
