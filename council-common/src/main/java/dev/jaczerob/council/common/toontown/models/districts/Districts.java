package dev.jaczerob.council.common.toontown.models.districts;

import dev.jaczerob.council.common.toontown.models.ToontownObject;
import dev.jaczerob.council.common.toontown.models.invasions.InvasionProperties;
import dev.jaczerob.council.common.toontown.models.invasions.Invasions;
import dev.jaczerob.council.common.toontown.models.population.Population;

import java.util.*;

public record Districts(
        List<District> districts,
        String error
) implements ToontownObject {
    public static Districts fromInvasionsAndPopulation(final Invasions invasions, final Population population) {
        final Set<String> districtNames = population.populationByDistrict().keySet();
        final List<District> districtsList = new ArrayList<>();

        for (final String districtName : districtNames) {
            final int districtPopulationCount = population.populationByDistrict().get(districtName);
            final InvasionProperties districtInvasionProperties = invasions.invasions().get(districtName);
            final String districtStatus = population.statusByDistrict().get(districtName);
            final District district = new District(
                    districtPopulationCount,
                    districtName,
                    districtInvasionProperties,
                    districtStatus
            );

            districtsList.add(district);
        }

        final Comparator<District> comparator = Comparator
                .comparing(District::population)
                .reversed();

        districtsList.sort(comparator);
        return new Districts(districtsList, "");
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Districts other)) {
            return false;
        }

        if (this.districts() == null || other.districts() == null) {
            if (this.districts() != null) {
                return false;
            } else return other.districts() == null;
        }

        if (this.districts().size() != other.districts().size()) {
            return false;
        }

        for (int i = 0; i < this.districts().size(); i++) {
            if (!this.districts().get(i).equals(other.districts().get(i))) {
                return false;
            }
        }

        return this.error().equals(other.error());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.districts(), this.error());
    }
}
