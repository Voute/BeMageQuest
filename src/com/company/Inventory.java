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

    public void remove(Item item) {
        items.remove(item);
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
            printedItems.append(i + ": [" + items.get(i).name + "]\n");
        }
        System.out.println(printedItems);
        return printedItems.toString();
    }
}
