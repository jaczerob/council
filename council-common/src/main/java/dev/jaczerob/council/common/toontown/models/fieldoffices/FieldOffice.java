package dev.jaczerob.council.common.toontown.models.fieldoffices;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public record FieldOffice(
        @JsonSetter(nulls = Nulls.AS_EMPTY) String department,
        int difficulty,
        int annexes,
        boolean open,
        int expiring
) {
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FieldOffice other)) {
            return false;
        }

        return  this.department().equals(other.department()) &&
                this.difficulty() == other.difficulty() &&
                this.annexes() == other.annexes() &&
                this.open() == other.open() &&
                this.expiring() == other.expiring();
    }
}
