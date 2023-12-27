package dev.jaczerob.council.common.toontown.models.news;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record News(
        @JsonSetter(nulls = Nulls.AS_EMPTY) String title,
        int postId,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String author,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String body,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String date,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String image,
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error
) implements ToontownObject {
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof News other)) {
            return false;
        }

        return  this.title().equals(other.title()) &&
                this.postId() == other.postId() &&
                this.author().equals(other.author()) &&
                this.body().equals(other.body()) &&
                this.date().equals(other.date()) &&
                this.image().equals(other.image()) &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return this.postId();
    }
}
