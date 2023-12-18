package dev.jaczerob.council.common.models.news;

import dev.jaczerob.council.common.models.ToontownObject;

public record NewsPartial(
        String url,
        String title,
        String author,
        String date,
        String image,
        String error
) implements ToontownObject {

}
