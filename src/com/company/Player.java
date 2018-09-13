package com.company;

public class Player {
    private final String name;
    private final Inventory inventory;
    private Location location;
    private boolean isWinner;

    public Player(String name, Inventory inventory, Location location) {
        this.name = name;
        this.inventory = inventory;
        this.location = location;
        this.isWinner = false;
    }

    public void lookAround() {
        System.out.println("Ты осматриваешься вокруг себя.");
        location.printDescription();
        String locationItemsOutput = location.getPrintedItems();
        if (locationItemsOutput == "") {
            System.out.println("К сожалению тут нет ничего, на что стоило бы еще обратить внимание.");
        } else {
            System.out.println("В некоторых местах можно увидеть интересные предметы и не только. Наверное вот это можно как-то использовать:");
            System.out.println(locationItemsOutput);
        }

    }

    public void go(String direction) {

    }

    public void take(String itemName) {
        Item item = location.findItemByName(itemName);
        if (item == null) {
            System.out.println("Ты ищешь вокруг себя [" + itemName + "], но не находишь ничего похожего.");
        } else {
            location.removeItem(item);
            inventory.add(item);
            System.out.println("Ты берешь [" + item.name + "] и прячешь куда-то в одежду.");
        }
    }

    public void use(String object, String target) {
        Item objectItem = inventory.findByName(object);
        // проверяем, есть ли объект в инвентаре игрока
        if (objectItem == null) {
            System.out.println("Ты очень хочешь использовать [" + object + "], но его не оказывается под рукой." +
                    " Хорошо бы иметь его при себе.");
        } else {
            Item targetItem = inventory.findByName(target);

            // проверяем, есть ли цель в инвенторях игрока и локации
            if (targetItem == null) targetItem = location.findItemByName(target);
            if (targetItem == null) {
                System.out.println("Ты ищешь [" + target + "], но его нигде нет.");
            } else {
                Item newItem = GameObjects.combine(objectItem, targetItem);
                if (newItem == null) {
                    System.out.println("Ты пробуешь применить [" + objectItem.name + "] к [" + targetItem.name + ", " +
                            "но ничего путного не получается.");
                } else {
                    System.out.println("Ты используешь [" + objectItem.name + "] на [" + targetItem.name + ", " +
                            "и у тебя получается [" + newItem.name + "].");
                }
            }
        }
    }

    public void inspect(String itemName) {
        Item item = inventory.findByName(itemName);
        if (item == null) {
            System.out.println("Ты ищешь у себя [" + itemName + "], но не находишь ничего похожего.");
        } else {
            System.out.println("Ты достаешь [" + item.name + "] и внимательно изучаешь.");
            item.printDescription();
        }
    }

    public void checkInventory() {
        String itemsOutput = inventory.getPrintedItems();
        if (itemsOutput == "") {
            System.out.println("Ты обшариваешь свою одежду, но ничего не находишь.");
        } else {
            System.out.println("Ты обшариваешь свою одежду и обнаруживаешь следующие предметы:");
            System.out.println(itemsOutput);
        }

    }
}
