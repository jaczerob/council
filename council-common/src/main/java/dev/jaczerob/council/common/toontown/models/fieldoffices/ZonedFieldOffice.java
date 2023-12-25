package dev.jaczerob.council.common.toontown.models.fieldoffices;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

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
