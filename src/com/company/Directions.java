package com.company;

public enum Directions {
    север,
    юг,
    запад,
    восток,
    наверх,
    вниз;

    public static Directions findDirectionByName(String directionName) {
        for (Directions direction : values()) {
            if (direction.name().equals(directionName)) return direction;
        }
        return null;
    }
}
