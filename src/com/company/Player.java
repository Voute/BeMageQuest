package com.company;

public class Player {
    final String printedName;
    private final String name;
    private final Inventory inventory;
    private Location location;
    private boolean isWinner;


    public Player(String name, Inventory inventory, Location location) {
        this.name = name;
        this.printedName = "[" + name + "]";
        this.inventory = inventory;
        this.location = location;
        this.isWinner = false;
    }

    public void lookAround() {
        System.out.println(printedName + " осматривается вокруг себя.");
        String locationItemsOutput = location.getAllItemsDescription();
        if (locationItemsOutput == "") {
            System.out.println("К сожалению тут нет ничего, на что стоило бы еще обратить внимание.");
        } else {
            System.out.println("В некоторых местах можно увидеть интересные предметы и не только. Наверное вот это можно как-то использовать:");
            System.out.println(locationItemsOutput);
        }

    }

    public void go(String direction) {
        Directions dir = Directions.findDirectionByName(direction);
        if (dir != null) {
            Location newLocation = location.getLocation(Directions.valueOf(direction));

            if (newLocation != null) {
                location = newLocation;
                System.out.println(printedName + " перемещается в " + newLocation.printedName);
                System.out.println(newLocation.description);
            } else {
                System.out.println(printedName + " пытается пойти по направлению '" + direction + "', но там некуда идти.");
            }

        } else {
            System.out.println(printedName + " пытается определиться, где направление '" + direction +
            "', но ничего не получается.");
        }

    }

    public void take(String itemName) {
        Item item = location.findItemByName(itemName);
        if (item == null) {
            System.out.println(printedName + " ищет вокруг себя [" + itemName + "], но не находит ничего похожего.");
        } else {
            if (location.removeItem(item)) {
                inventory.add(item);
                if (item.name.equals("кристалл")) {
                    isWinner = true;
                } else {
                    System.out.println(printedName + " берет " + item.printedName + " и прячет куда-то в одежду.");
                }
            } else {
                System.out.println(printedName + " пытается взять " + item.printedName + ", но что-то ему мешает. Не то вес, не то boolean значение поля предмета.");
            }
        }
    }

    public void use(String objectName, String target) {
        Item objectItem = inventory.findByName(objectName);
        // проверяем, есть ли объект в инвентаре игрока
        if (objectItem == null) {
            System.out.println(printedName + " очень хочет использовать [" + objectName + "], но его не оказывается под рукой." +
                    " Хорошо бы иметь [" + objectName +  "] при себе.");
            return;
        }

        Item targetItem = inventory.findByName(target);

        // проверяем, есть ли цель в инвенторях игрока и локации
        if (targetItem == null) targetItem = location.findItemByName(target);
        if (targetItem == null) {
            System.out.println(printedName + " ищет [" + target + "], но его нигде нет.");
            return;
        }

        Combo combo = ComboWarehouse.findCOmbo(objectItem, targetItem);
        if (combo == null) {
            System.out.println(printedName + " пробует применить " + objectItem.printedName + " к " + targetItem.printedName + ", " +
                    "но ничего путного не получается.");
            return;
        }

        Item newItem = combo.result;
        System.out.println(combo.message);
        if (newItem != null) {
            if (newItem.name.equals("кристалл")) {
                location.addItem(newItem);
            } else {
                inventory.add(newItem);
            }
        }
        if (combo.removeObjectAfterUsage) {
            inventory.remove(objectItem);
        }
        if (combo.removeTargetAfterUsage) {
            if (inventory.remove(targetItem)) location.removeItem(targetItem);

        }
    }

    public void inspect(String itemName) {
        Item item = inventory.findByName(itemName);
        if (item == null) item = location.findItemByName(itemName);
        if (item == null) {
            System.out.println(printedName + " ищет [" + itemName + "], но не находит ничего похожего.");
        } else {
            System.out.println(printedName + " достает " + item.printedName + " и внимательно изучает.");
            System.out.println(item.description);
        }
    }

    public void checkInventory() {
        String itemsOutput = inventory.toString();
        if (itemsOutput == "") {
            System.out.println(printedName + " обшаривает свою одежду, но ничего не находит.");
        } else {
            System.out.println(printedName + " обшаривает свою одежду и обнаруживает следующие предметы:");
            System.out.println(itemsOutput);
        }

    }

    public Location getLocation() {
        return location;
    }

    public boolean getWinnerStatus() {
        return isWinner;
    }

}
