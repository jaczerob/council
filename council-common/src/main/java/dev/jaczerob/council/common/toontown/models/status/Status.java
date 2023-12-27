package dev.jaczerob.council.common.toontown.models.status;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Objects;

public record Status(
        boolean open,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String status,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Status other)) {
            return false;
        }

        return  this.open() == other.open() &&
                this.status().equals(other.status()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.open(), this.status(), this.error());
    }
}
