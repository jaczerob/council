package dev.jaczerob.council.common.toonstats.models;

import java.io.Serializable;

public record Toon(
    int id,
    int species,
    int laff,
    int gagToonup,
    int gagTrap,
    int gagLure,
    int gagSound,
    int gagThrow,
    int gagSquirt,
    int gagDrop,
    int organic,
    int sellbot,
    int cashbot,
    int lawbot,
    int bossbot
) implements Serializable {

}
