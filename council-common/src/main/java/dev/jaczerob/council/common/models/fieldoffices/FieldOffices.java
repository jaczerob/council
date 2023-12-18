package dev.jaczerob.council.common.models.fieldoffices;

import dev.jaczerob.council.common.models.ToontownObject;

import java.util.*;

public record FieldOffices(
        int lastUpdated,
        Map<String, FieldOffice> fieldOffices,
        String error
) implements ToontownObject {
}
