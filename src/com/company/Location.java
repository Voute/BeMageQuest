package com.company;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private final String description;
    private final Inventory inventory;
    final String name;
    final Map<Directions, Location> paths;

    public Location(String name, String description, Inventory inventory, Map<Directions, Location> paths) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.paths = paths;
    }

    public void printDescription() {
        System.out.println(description);
    }

    public String getPrintedItems() {
        return inventory.getPrintedItems();
    }

    public Item findItemByName(String itemName) {
        return inventory.findByName(itemName);
    }

    public boolean removeItem(Item item) {
        if (item.isMovable) {
            inventory.remove(item);
            return true;
        }
        return false;
    }
}
