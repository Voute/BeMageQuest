package com.company;

public class Combo {
    final Item target;
    final Item object;
    final Item result;
    final String message;

    public Combo(Item target, Item object, Item result, String message) {
        this.target = target;
        this.object = object;
        this.result = result;
        this.message = message;
    }

}
