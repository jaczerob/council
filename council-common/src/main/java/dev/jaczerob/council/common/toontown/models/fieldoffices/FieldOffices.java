package dev.jaczerob.council.common.toontown.models.fieldoffices;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.*;

public record FieldOffices(
        int lastUpdated,
        @JsonSetter(nulls = Nulls.AS_EMPTY) Map<String, FieldOffice> fieldOffices,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FieldOffices other)) {
            return false;
        }

        if (this.fieldOffices() == null || other.fieldOffices() == null) {
            if (this.fieldOffices() != null) {
                return false;
            } else return other.fieldOffices() == null;
        }

        if (this.fieldOffices().size() != other.fieldOffices().size()) {
            return false;
        }

        for (final String fieldOfficeName : this.fieldOffices().keySet()) {
            if (!this.fieldOffices().get(fieldOfficeName).equals(other.fieldOffices().get(fieldOfficeName))) {
                return false;
            }
        }

        return  this.lastUpdated() == other.lastUpdated() &&
                this.error().equals(other.error());
    }
}
