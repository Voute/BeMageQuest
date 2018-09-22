package com.company;

import com.sun.deploy.util.SystemUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class BeMageQuest {

    private static Scanner consoleInput;
    private final static String[] NAME_ENDINGS = {"Упертый Баг", "Магический Кейс", "Стремительный Хотфикс", "Уставший Ордер"};
    private final static String[] COMMANDS_HELP = {"***[команда] или [сокращенный вариант команды]: описание действия - пример команды***",
            "[команды] или [к]: выводит на экран список доступных команд",
            "[выход] или [в]: завершает квест",
            "[осмотреться] или [о]: показывает описание локации, в которой находится персонаж",
            "[идти {направление}] или [и {направление}]: направляет персонажа перейти по указанному направлению в другую локацию - идти запад\n" +
            "Возможные направления: север, юг, запад, восток, наверх, вниз",
            "[забрать {предмет}] или [з {предмет}]: указывает персонажу забрать предмет с локации - забрать лягушка",
            "[применить {предмет1} {предмет2}] или [п {предмет1} {предмет2}]: побуждает персонажа попробовать применить предмет1 к предмету 2 - применить лягушка маг",
            "[рассмотреть {предмет}] или [р {предмет}]: показывает описание предмета - рассмотреть лягушка",
            "[сумка] или [с]: показывает предметы, которые у персонажа при себе"
    };

    public static void main(String[] args) throws IOException {

        consoleInput = new Scanner(System.in);
        consoleInput.useLocale(new Locale("ru"));
        System.out.println("Добро пожаловать в Be Mage Quest!");
        String playerName = getPlayerInput("Как твое имя?");
        System.out.println("Приветствую тебя, " + playerName + "!");
        playerName += " " + NAME_ENDINGS[new Random().nextInt(NAME_ENDINGS.length - 1)];
        Player player = initiateGame(playerName);
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
//        try {
//            userCommand = new String(userCommand.getBytes(), "windows-1251");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } finally {
            return userCommand;
//        }
    }

    private static String getPlayerInput() {
        return getPlayerInput("");
    }

    private static void playQuest(Player player) {
        System.out.println("\n" + player.printedName + " сейчас находится в " + player.getLocation().printedName + ".");
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
                    if (commandSyntaxValid(commandWords, 2)) player.go(commandWords[1]);
                    break;
                case "забрать":
                case "з":
                    if (commandSyntaxValid(commandWords, 2)) player.take(commandWords[1]);
                    break;
                case "применить":
                case "п":
                    if (commandSyntaxValid(commandWords, 3)) player.use(commandWords[1], commandWords[2]);
                    break;
                case "рассмотреть":
                case "р":
                    if (commandSyntaxValid(commandWords, 2)) player.inspect(commandWords[1]);
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
            if (!player.getWinnerStatus()) {
                playerCommand = getPlayerInput();
            }
        }

        if (player.getWinnerStatus()) {
            System.out.println(player.printedName + " берет в руки кристалл и завороженно смотрит на него. Внутри кристалла" +
                    " видны искрящиеся звезды, напоминающие одновременно галактику и ярый поток логов после старта сервера. " +
                    "Кристалл теплый и совсем легкий. По руке проходит мелкая дрожь и кажется что сила наполняет " + player.printedName +
                    " могуществом. Но оказывается что это откуда-то взявшаяся лягушка села на руку и бессовестно гадит, палясь на кристалл. " +
                    player.printedName + " кладет кристалл в карман и тут же исчезает, оставляя за собой шлейф. Оставшаяся на полу лягушка " +
                    "досадно урлыкнула, намекая на то, что логи с дев серверов теперь придется мониторить ей."
            );
            System.out.println("Поздравляю! Ты прошел квест!");
        }
    }

    private static void printHelp() {
        System.out.println("В игре доступны следующие команды:");
        for (String s : COMMANDS_HELP) {
            System.out.println(s);
        }
    }

    private static boolean commandSyntaxValid(Object[] array, int minimumWords) {
        if (array.length >= minimumWords) return true;
        else {
            printHelp();
            return false;
        }
    }

    private static Player initiateGame(String playerName) {
        Item well = new Item("колодец", "Глубокий и пахнет сыростью. Интересно, а старик воду фильтрует прежде чем пить?", false);
        Item mage = new Item("маг", "Храпит, старый хрен. Как бы у тебя кристалл вытащить?", false);
        Item burner = new Item("горелка", "Большая, но еще теплая. Наверняка недавно работала. Интересно," +
                " ее можно использовать не по назначению?", false);

        Item frog = new Item("лягушка", "Тыкаешь - квакает. Не трогаешь - сидит и внимательно на тебя смотрит.", true);
        Item frogAndMarker = new Item("лягушка", "Перестала квакать и заметно удлиннилась. Наверное, маркер мешается.", true);
        Item crystal = new Item("кристалл", "Такой глубокий, синий цвет. Так и манит окунуться и забыться.", true);
        Item bucket = new Item("ведро", "Ржавое, но без дырок. Еще послужит, если использовать осторожно.", true);
        Item bucketAndChain = new Item("ведро", "С цепью кажется от него будет больше пользы чем без. Но толку от цепи, валяющейся на дне?", true);
        Item chainedBucket = new Item("ведро", "Цепь крепко припаяна. Но зачем?", true);
        Item bucketAndWater = new Item("ведро", "Полное воды. Хоть цветы поливай, хоть себя, хоть лягушку топи.", true);
        Item chain = new Item("цепь", "Длинная и легкая. Вот бы такую куда-нибудь закинуть!", true);
        Item marker = new Item("маркер", "Черный, затасканный. Наверное он несмываемый, потому что пахнет бодряще.", true);

        ArrayList<Combo> combos = new ArrayList<>();

        // операции с лягушкой
        combos.add(new Combo(frog, marker,
                frogAndMarker,
                "Какой длинный маркер... Но ничего, поместился.",
                true,
                true
        ));
        combos.add(new Combo(frog, well,
                null,
                "'Что я там забыла? Сам туда лезь.', - внезапно говорит " +
                "лягушка человеческим голосом. Причем и не женским и не мужским.",
                false,
                false
        ));
        combos.add(new Combo(frog, mage,
                null,
                "Лягушка шлепается на лицо старику и долго елозит по нему, " +
                "но тот не просыпается. Лягушка грустнеет и возвращается к хозяину.",
                false,
                false
        ));
        combos.add(new Combo(frog, bucket,
                null,
                "Лягушка смотрит на хозяина как на идиота и не понимает " +
                " зачем ей прыгать в пустое ведро.",
                false,
                false
        ));
        combos.add(new Combo(frog, crystal,
                null,
                "Лягушка садится на кристалл и резко начинает синеть. " +
                "Потом крепко выругивается, кастует себе портал и исчезает в нем.",
                true,
                false
        ));
        combos.add(new Combo(frog, bucketAndWater,
                null,
                "Лягушка радостно плюхается в ведро и долго там " +
                "плещется. Потом довольная вылезает обратно и возвращается к хозяину.",
                false,
                false
        ));

        combos.add(new Combo(frogAndMarker, bucketAndWater,
                null,
                "Лягушка плюхается в воду. " +
                "Пытается поплавать, но благодаря маркеру постоянно всплывает. Она просится обратно на руки " +
                "и персонаж забирает ее из ведра.",
                false,
                false
        ));

        // операции с ведром
        combos.add(new Combo(chain, bucket,
                bucketAndChain,
                "Цепь положена в ведро. " +
                "Но это выглядит как-то странно. Может лучше прикрепить ее понадежнее?",
                true,
                true
        ));
        combos.add(new Combo(bucketAndChain, burner,
                chainedBucket,
                "Цепь приварена к ведру.",
                true,
                false
        ));
        combos.add(new Combo(chainedBucket, well,
                bucketAndWater,
                "В ведро набрана до краев колодезная вода.",
                true,
                false
        ));
        combos.add(new Combo(bucketAndWater, mage,
                crystal, "Маг щедро обливается студеной водой " +
                "из ржавого ведра. Он что-то сурово мычит и переворачивается на другой бок." +
                "Откуда-то из-под его пазухи на пол выпадает что-то блеснувшшее синим цветом.",
                false,
                false
        ));

        // операции с маркером
        combos.add(new Combo(marker, frog,
                null,
                "Лягушке нарисовали контуры очков. Теперь она похожа " +
                "на разработчика квеста.",
                false,
                false
        ));
        combos.add(new Combo(marker, bucket,
                null,
                "Ведро аккуратно помечается именем хозяина.  " +
                "Так оно выглядит гораздо солиднее.",
                false,
                false
        ));
        combos.add(new Combo(marker, mage,
                null,
                "На проплешине старика компактно дублируется сегодняшний лог с дев сервера. " +
                "На лбу у него теперь красуется надпись: 'PASSED WITH MINOR DEFECTS'.",
                false,
                false
        ));

        ArrayList<Location> locations = new ArrayList<>();

        Location villageRoom = new Location("комната",
                "Это комната старика. Сам он валяется на тахте и громко храпит. " +
                "Хорошо бы проветрить помещение, дышать совсем нечем. Как бы выудить у него кристалл?"
        );
        villageRoom.addItem(mage);
        locations.add(villageRoom);

        Location villageLoft = new Location("чердак",
                "Это чердак старого дома. Тут пыльно и незапыленная только узкая дорожка " +
                "от лестницы к горелке."
        );
        villageLoft.addItem(burner);
        locations.add(villageLoft);

        Location garden = new Location("сад",
                "Это сад. Как тут свежо и хорошо! Неохота возвращаться в дом, жить бы прямо тут, на улице."
        );
        garden.addItem(frog);
        garden.addItem(well);
        garden.addItem(bucket);
        garden.addItem(chain);
        locations.add(garden);

        villageRoom.addPath(Directions.восток, garden);
        villageRoom.addPath(Directions.наверх, villageLoft);

        villageLoft.addPath(Directions.вниз, villageRoom);

        garden.addPath(Directions.запад, villageRoom);

        ComboWarehouse.setCombos(combos);

        Inventory playerInventory = new Inventory();
        playerInventory.add(marker);

        return new Player(playerName, playerInventory, villageRoom);
    }
}
