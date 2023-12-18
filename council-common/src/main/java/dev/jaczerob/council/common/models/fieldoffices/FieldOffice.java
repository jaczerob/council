package dev.jaczerob.council.common.models.fieldoffices;

public record FieldOffice(
        String department,
        int difficulty,
        int annexes,
        boolean open,
        int expiring
) {

}
