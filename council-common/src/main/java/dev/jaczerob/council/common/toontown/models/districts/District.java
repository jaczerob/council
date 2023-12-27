package dev.jaczerob.council.common.toontown.models.districts;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;
import dev.jaczerob.council.common.toontown.models.invasions.InvasionProperties;

import java.util.Objects;

public record District(
        int population,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String name,
        InvasionProperties invasion,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String status
) implements ToontownObject {
    @Override
    public String error() {
        return "";
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof District other)) {
            return false;
        }

        if (this.invasion() == null || other.invasion() == null) {
            if (this.invasion() != null) {
                return false;
            } else return other.invasion() == null;
        }

        return  this.name().equals(other.name()) &&
                this.population() == other.population() &&
                this.status().equals(other.status());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name(), this.population(), this.status(), this.invasion());
    }
}
