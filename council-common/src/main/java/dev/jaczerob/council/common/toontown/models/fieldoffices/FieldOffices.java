package dev.jaczerob.council.common.toontown.models.fieldoffices;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.*;

public record FieldOffices(
        int lastUpdated,
        Map<String, FieldOffice> fieldOffices,
        String error
) implements ToontownObject {
}
