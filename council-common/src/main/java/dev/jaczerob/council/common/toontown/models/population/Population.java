package dev.jaczerob.council.common.toontown.models.population;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import dev.jaczerob.council.common.toontown.models.ToontownObject;

import java.util.Map;

public record Population(
        @JsonSetter(nulls = Nulls.AS_EMPTY) String error,
        int lastUpdated,
        int totalPopulation,
        @JsonSetter(nulls = Nulls.AS_EMPTY) Map<String, Integer> populationByDistrict,
        @JsonSetter(nulls = Nulls.AS_EMPTY) Map<String, String> statusByDistrict
) implements ToontownObject {
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Population other)) {
            return false;
        }

        if (this.populationByDistrict() == null || other.populationByDistrict() == null) {
            if (this.populationByDistrict() != null) {
                return false;
            } else return other.populationByDistrict() == null;
        }

        if (this.populationByDistrict().size() != other.populationByDistrict().size()) {
            return false;
        }

        for (final String districtName : this.populationByDistrict().keySet()) {
            if (!this.populationByDistrict().get(districtName).equals(other.populationByDistrict().get(districtName))) {
                return false;
            }
        }

        if (this.statusByDistrict() == null || other.statusByDistrict() == null) {
            if (this.statusByDistrict() != null) {
                return false;
            } else return other.statusByDistrict() == null;
        }

        if (this.statusByDistrict().size() != other.statusByDistrict().size()) {
            return false;
        }

        for (final String districtName : this.statusByDistrict().keySet()) {
            if (!this.statusByDistrict().get(districtName).equals(other.statusByDistrict().get(districtName))) {
                return false;
            }
        }

        return  this.lastUpdated() == other.lastUpdated() &&
                this.totalPopulation() == other.totalPopulation() &&
                this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return this.totalPopulation();
    }
}
