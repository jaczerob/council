package dev.jaczerob.council.common.models.releasenotes;


import dev.jaczerob.council.common.models.ToontownObject;

public record ReleaseNotesPartial(
        int noteId,
        String slug,
        String date,
        String error
) implements ToontownObject {
}
