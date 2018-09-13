package com.company;

import java.util.HashMap;
import java.util.Map;

public class Location {

    final String name;
    final String printedName;
    final String description;
    final Map<Directions, Location> paths;
    private final Inventory inventory;

    public Location(String name, String description, Inventory inventory, Map<Directions, Location> paths) {
        this.name = name;
        printedName = "*" + name + "*";
        this.description = description;
        this.inventory = inventory;
        this.paths = paths;
    }

    public String getPrintedItems() {
        return inventory.getPrintedItems();
    }

    public Item findItemByName(String itemName) {
        return inventory.findByName(itemName);
    }

    public boolean removeItem(Item item) {
        if (item.isMovable) {
            return inventory.remove(item);
        }
        return false;
    }

}
