package dev.jaczerob.council.common.toontown.models.releasenotes;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record ReleaseNotesPartial(
        int noteId,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String slug,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String date,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ReleaseNotesPartial other)) {
            return false;
        }

        return  this.noteId() == other.noteId() &&
                this.slug().equals(other.slug()) &&
                this.date().equals(other.date()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return this.noteId();
    }
}
