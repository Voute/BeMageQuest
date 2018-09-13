package com.company;

public class Combo {
    final Item target;
    final Item object;
    final Item result;
    final String message;
    final boolean removeObjectAfterUsage;
    final boolean removeTargetAfterUsage;

    public Combo(Item object, Item target, Item result, String message, boolean removeObjectAfterUsage, boolean removeTargetAfterUsage) {
        this.target = target;
        this.object = object;
        this.result = result;
        this.message = message;
        this.removeObjectAfterUsage = removeObjectAfterUsage;
        this.removeTargetAfterUsage = removeTargetAfterUsage;
    }

}
