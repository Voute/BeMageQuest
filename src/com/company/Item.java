package com.company;

public class Item {
    final String description;
    final String name;
    final String printedName;
    final boolean isMovable;

    public Item(String name, String description, boolean isMovable) {
        this.name = name;
        printedName = "[" + name + "]";
        this.description = description;
        this.isMovable = isMovable;
    }

}
