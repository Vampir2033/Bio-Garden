package ru.dorogin.biogarden.gameplay.dna;

import lombok.Getter;

public enum Relationship {
    RELATIVES(10),              // родственники
    ONE_SPECIES(50),            // один вид
    DIFFERENT(Integer.MAX_VALUE),      // разные виды
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
