package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboWarehouse {
    private static List<Combo> combos;

    public static void setCombos(List<Combo> initCombos) {
        combos = initCombos;
    }

    public static Combo findCOmbo(Item object, Item target) {
        for (Combo combo : combos) {
            if (combo.object == object && combo.target == target) {
                return combo;
            }
        }
        return null;
    }

}
