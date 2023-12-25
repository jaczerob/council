package dev.jaczerob.council.common.toontown.models.news;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record News(
        String title,
        int postId,
        String author,
        String body,
        String date,
        String image,
        String error
) implements ToontownObject {

}
