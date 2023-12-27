package dev.jaczerob.council.common.toontown.models.invasions;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Objects;

public record InvasionProperties(
        int asOf,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String type,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String progress,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof InvasionProperties other)) {
            return false;
        }

        return  this.type().equals(other.type()) &&
                this.progress().equals(other.progress()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type(), this.progress(), this.error());
    }
}
