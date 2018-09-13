package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObjects {
    private static final List<Combo> combos;
    private static final List<Item> items;
    private static final List<Location> locations;
    static final Location playerStartLocation;

    // initialize game objects, game objects combination rules
    static {
        combos = new ArrayList<>();
        combos.add(new Combo(null, null, null, ""));

        locations = new ArrayList<>();
        locations.add(new Location("", "", new Inventory(), new HashMap<Directions, Location>()));
        playerStartLocation = new Location("", "", new Inventory(), new HashMap<Directions, Location>();

        items = new ArrayList<>();
        items.add(new Item("", "", true));
    }

    public static Item combineItems(Item object, Item target) {
        for (Combo combo : combos) {
            if (combo.object == object && combo.target == target) {
                return combo.result;
            }
        }
        return null;
    }
}
