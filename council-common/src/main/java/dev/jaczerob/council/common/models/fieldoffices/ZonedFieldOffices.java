package dev.jaczerob.council.common.models.fieldoffices;

import dev.jaczerob.council.common.models.ToontownObject;

import java.util.*;

public record ZonedFieldOffices(
    String error,
    List<ZonedFieldOffice> fieldOffices
) implements ToontownObject {
    private static final Map<String, String> ZONE_ID_LOOKUP;

    static {
        ZONE_ID_LOOKUP = new HashMap<>();
        ZONE_ID_LOOKUP.put("3100", "Walrus Way");
        ZONE_ID_LOOKUP.put("3200", "Sleet Street");
        ZONE_ID_LOOKUP.put("3300", "Polar Place");
        ZONE_ID_LOOKUP.put("4100", "Alto Avenue");
        ZONE_ID_LOOKUP.put("4200", "Baritone Boulevard");
        ZONE_ID_LOOKUP.put("4300", "Tenor Terrace");
        ZONE_ID_LOOKUP.put("5100", "Elm Street");
        ZONE_ID_LOOKUP.put("5200", "Maple Street");
        ZONE_ID_LOOKUP.put("5300", "Oak Street");
        ZONE_ID_LOOKUP.put("9100", "Lullaby Lane");
        ZONE_ID_LOOKUP.put("9200", "Pajama Place");
    }
    public static ZonedFieldOffices fromFieldOffices(final FieldOffices fieldOffices) {
        final Comparator<ZonedFieldOffice> comparator = Comparator
                .comparing(ZonedFieldOffice::difficulty);

        final List<ZonedFieldOffice> formattedFieldOffices = new ArrayList<>();
        for (final Map.Entry<String, FieldOffice> entry : fieldOffices.fieldOffices().entrySet()) {
            formattedFieldOffices.add(new ZonedFieldOffice(
                    "sellbot",
                    entry.getValue().difficulty() + 1,
                    entry.getValue().annexes(),
                    entry.getValue().open(),
                    entry.getValue().expiring(),
                    convertZoneIDToZone(entry.getKey())
            ));
        }

        formattedFieldOffices.sort(comparator);

        return new ZonedFieldOffices(
                fieldOffices.error(),
                formattedFieldOffices
        );
    }

    private static String convertZoneIDToZone(final String zoneID) {
        return ZONE_ID_LOOKUP.getOrDefault(zoneID, "Zone Not Found");
    }
}
