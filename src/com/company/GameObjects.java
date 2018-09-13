package com.company;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    private static final List<Combo> combos;
    private static final List<Item> items;

    // initialize game objects, game objects combination rules
    static {
        combos = new ArrayList<>();
        combos.add(new Combo(null, null, null, ""));

        items = new ArrayList<>();
        items.add(new Item("", "", true));
    }

    public static Item combine(Item object, Item target) {
        for (Combo combo : combos) {
            if (combo.object == object && combo.target == target) {
                return combo.result;
            }
        }
        return null;
    }
}
