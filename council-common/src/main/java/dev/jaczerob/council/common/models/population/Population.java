package dev.jaczerob.council.common.models.population;

import dev.jaczerob.council.common.models.ToontownObject;

import java.util.Map;

public record Population(
        String error,
        int lastUpdated,
        int totalPopulation,
        Map<String, Integer> populationByDistrict,
        Map<String, String> statusByDistrict
) implements ToontownObject {

}
