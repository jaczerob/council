package dev.jaczerob.council.common.toonstats.models;

import java.io.Serializable;
import java.util.List;

public record Toons(
        List<Toon> toons
) implements Serializable {
}
