package ru.dorogin.biogarden.gameplay.dna;

import lombok.Getter;

import static ru.dorogin.biogarden.GlobalVars.*;

public enum Relationship {
    RELATIVES(RELATIVES_MIN_DNK_DIFF),      // родственники
    ONE_SPECIES(ONE_SPECIES_MIN_DNK_DIFF),  // один вид
    DIFFERENT(DIFFERENT_MIN_DNK_DIFF),      // разные виды
    ;

    @Getter
    private final int maxDiff;

    Relationship(int maxDiff) {
        this.maxDiff = maxDiff;
    }

    public static Relationship getRelationshipByDifference(int diff){
        if(diff < RELATIVES.getMaxDiff()) {
            return RELATIVES;
        } else if (diff < ONE_SPECIES.getMaxDiff()) {
            return ONE_SPECIES;
        } else {
            return DIFFERENT;
        }
    }
}
