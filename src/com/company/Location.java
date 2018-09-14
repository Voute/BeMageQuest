package com.company;

import java.util.HashMap;
import java.util.Map;

public class Location {

    final String name;
    final String printedName;
    final String description;
    private final Map<Directions, Location> paths;
    private final Inventory inventory;

    public Location(String name, String description) {
        this.name = name;
        printedName = "*" + name + "*";
        this.description = description;
        paths = new HashMap<>();
        inventory = new Inventory();
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

    public void addPath(Directions direction, Location location) {
        paths.put(direction, location);
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Location getLocation(Directions direction) {
        return paths.get(direction);
    }
}
