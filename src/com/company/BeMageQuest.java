package com.company;

import java.util.Random;
import java.util.Scanner;

public class BeMageQuest {

    private final static Scanner consoleInput;
    private final static String[] NAME_ENDINGS = {"Упертый Баг", "Магический Кейс", "Стремительный Хотфикс", "Уставший Ордер"};
    private final static String[] COMMANDS_HELP = {"***[команда] или [сокращенный вариант команды]: описание действия - пример команды***",
            "[команды] или [к]: выводит на экран список доступных команд",
            "[выход] или [в]: завершает квест",
            "[осмотреться] или [о]: показывает описание локации, в которой находится персонаж",
            "[идти {направление}] или [и {направление}]: направляет персонажа перейти по указанному направлению в другую локацию - идти запад",
            "[забрать {предмет}] или [з {предмет}]: указывает персонажу забрать предмет с локации - забрать лягушка",
            "[применить {предмет1} {предмет2}] или [п {предмет1} {предмет2}]: побуждает персонажа попробовать применить предмет1 к предмету 2 - применить лягушка маг",
            "[рассмотреть {предмет}] или [р {предмет}]: показывает описание предмета - рассмотреть лягушка",
            "[сумка] или [с]: показывает предметы, которые у персонажа при себе"
    };

    static {
        consoleInput = new Scanner(System.in);
    }

    public static void main(String[] args) {

        System.out.println("Добро пожаловать в Be Mage Quest!");
        String playerName = getPlayerInput("Как твое имя?");
        System.out.println("Приветствую тебя, " + playerName + "!");
        playerName += " " + NAME_ENDINGS[new Random().nextInt(NAME_ENDINGS.length - 1)];
        Player player = GameObjects.createPlayer(playerName);
        System.out.println("\nДля тебя создан персонаж. Он будет носить имя " + player.printedName + ".");
        System.out.println(player.printedName + " давно хочет стать Настоящим Магом и перестать подрабатывать в фрилансе, исправляя " +
                "просроченные скучные баги и колдуя однотипные верстки для сайтов. Но Старый [маг] говорит, что еще рано пользоваться " +
                "Настоящей Силой ибо она подвластна немногим и может уронить любой прод. Он каждый день заставляет " +
                "перебирать низкоприоритетные тикеты и изучать логи дев серверов.\n И вот, сегодня " + player.printedName + " " +
                "надоело работать на нудного старика. Когда-то он оговорился, что обрести Настоящую Силу можно лишь коснувшись [кристалла]. " +
                "Старый [маг] постоянно носит этот [кристалл] за пазухой в мантии и ни разу не доставал его оттуда." +
                "\nПомоги " + player.printedName + " стать Настоящим Магом.\n"
        );
        printHelp();
        playQuest(player);
    }

    private static String getPlayerInput(String message) {
        String userCommand = "";
        System.out.println(message);
        while (userCommand.equals("")) {
            userCommand = consoleInput.nextLine();
        }
        return userCommand;
    }

    private static String getPlayerInput() {
        return getPlayerInput("");
    }

    private static void playQuest(Player player) {
        System.out.println(player.printedName + " находится в " + player.getLocation().printedName + ".");
        System.out.println(player.getLocation().description);
        String playerCommand = getPlayerInput();
        String[] commandWords;

        while(!(playerCommand.equals("выход") || playerCommand.equals("в") || player.getWinnerStatus())) {
            commandWords = playerCommand.split(" ");
            switch (commandWords[0]) {
                case "команды":
                case "к":
                    printHelp();
                    break;
                case "осмотреться":
                case "о":
                    player.lookAround();
                    break;
                case "идти":
                case "и":
                    player.go(commandWords[1]);
                    break;
                case "забрать":
                case "з":
                    player.take(commandWords[1]);
                    break;
                case "применить":
                case "п":
                    player.use(commandWords[1], commandWords[2]);
                    break;
                case "рассмотреть":
                case "р":
                    player.inspect(commandWords[1]);
                    break;
                case "сумка":
                case "с":
                    player.checkInventory();
                    break;
                case "":
                    break;
                default:
                    printHelp();
                    break;
            }
            playerCommand = getPlayerInput();
        }
    }

    private static void printHelp() {
        System.out.println("В игре доступны следующие команды:");
        for (String s : COMMANDS_HELP) {
            System.out.println(s);
        }
    }
}
