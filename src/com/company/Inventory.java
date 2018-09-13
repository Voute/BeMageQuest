package com.company;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
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

    public String getPrintedItems() {
        StringBuilder printedItems = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            printedItems.append(items.get(i).printedName);
            if (i + 1 < items.size()) printedItems.append(", ");
        }
        System.out.println(printedItems);
        return printedItems.toString();
    }
}
