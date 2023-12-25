package dev.jaczerob.council.common.toontown.models.releasenotes;


import dev.jaczerob.council.common.toontown.models.ToontownObject;

public record ReleaseNotesPartial(
        int noteId,
        String slug,
        String date,
        String error
) implements ToontownObject {
}
