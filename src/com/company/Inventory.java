package com.company;

import java.util.*;

public class Inventory {
    private final Set<Item> items;

    public Inventory() {
        items = new HashSet<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public boolean remove(Item item) {
        return items.remove(item);
    }

    public Item findByName(String name) {
        for (Item item: items) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder printedItems = new StringBuilder();
        int i = 0;
        for (Item item: items) {
            printedItems.append(item.printedName);
            if ((i + 1) < items.size()) printedItems.append(", ");
            i++;
        }
        return printedItems.toString();
    }

}
