package dev.jaczerob.council.common.toontown.models.news;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Objects;

public record NewsPartial(
        @JsonSetter(nulls = Nulls.AS_EMPTY) String url,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String title,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String author,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String date,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String image,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof NewsPartial other)) {
            return false;
        }

        return  this.url().equals(other.url()) &&
                this.title().equals(other.title()) &&
                this.author().equals(other.author()) &&
                this.date().equals(other.date()) &&
                this.image().equals(other.image()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.url());
    }
}
