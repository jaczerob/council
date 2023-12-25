package dev.jaczerob.council.common.toontown.models.news;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record NewsPartial(
        String url,
        String title,
        String author,
        String date,
        String image,
        String error
) implements ToontownObject {

}
