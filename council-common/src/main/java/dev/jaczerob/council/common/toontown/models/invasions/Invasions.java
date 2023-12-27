package dev.jaczerob.council.common.toontown.models.invasions;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Map;

public record Invasions(
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error,
        int lastUpdated,
        @JsonSetter(nulls = Nulls.AS_EMPTY) Map<String, InvasionProperties> invasions
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Invasions other)) {
            return false;
        }

        if (this.invasions() == null || other.invasions() == null) {
            if (this.invasions() != null) {
                return false;
            } else return other.invasions() == null;
        }

        if (this.invasions().size() != other.invasions().size()) {
            return false;
        }

        for (final String invasionName : this.invasions().keySet()) {
            if (!this.invasions().get(invasionName).equals(other.invasions().get(invasionName))) {
                return false;
            }
        }

        return  this.lastUpdated() == other.lastUpdated() &&
                this.error().equals(other.error());
    }
}
