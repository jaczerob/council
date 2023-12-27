package dev.jaczerob.council.common.toontown.models.fieldoffices;

import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Objects;

public record ZonedFieldOffice(
        String department,
        int difficulty,
        int annexes,
        boolean open,
        int expiring,
        String zone
) implements ToontownObject {
    @Override
    public String error() {
        return "";
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ZonedFieldOffice other)) {
            return false;
        }

        return  this.department().equals(other.department()) &&
                this.difficulty() == other.difficulty() &&
                this.annexes() == other.annexes() &&
                this.open() == other.open() &&
                this.expiring() == other.expiring() &&
                this.zone().equals(other.zone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.department(),
                this.difficulty(),
                this.annexes(),
                this.open(),
                this.expiring(),
                this.zone()
        );
    }
}
