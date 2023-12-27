package dev.jaczerob.council.common.toontown.models.releasenotes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Objects;

public record ReleaseNotes(
        int noteId,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String slug,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String date,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String body,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ReleaseNotes other)) {
            return false;
        }

        return  this.noteId() == other.noteId() &&
                this.slug().equals(other.slug()) &&
                this.date().equals(other.date()) &&
                this.body().equals(other.body()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.noteId());
    }
}
