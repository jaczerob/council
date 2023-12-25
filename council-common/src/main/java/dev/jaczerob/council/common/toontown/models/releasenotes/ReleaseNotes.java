package dev.jaczerob.council.common.toontown.models.releasenotes;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record ReleaseNotes(
        int noteId,
        String slug,
        String date,
        String body,
        String error
) implements ToontownObject {
}
