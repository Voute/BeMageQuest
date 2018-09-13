package com.company;

public class Item {
    private final String description;
    final String name;
    final boolean isMovable;

    public Item(String name, String description, boolean isMovable) {
        this.name = name;
        this.description = description;
        this.isMovable = isMovable;
    }

    public void printDescription() {
        System.out.println(description);
    }
}
