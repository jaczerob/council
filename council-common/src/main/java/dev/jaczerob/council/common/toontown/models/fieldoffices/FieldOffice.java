package dev.jaczerob.council.common.toontown.models.fieldoffices;

public record FieldOffice(
        String department,
        int difficulty,
        int annexes,
        boolean open,
        int expiring
) {

}
