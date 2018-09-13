package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObjects {
    private static final List<Combo> combos;
    private static final List<Item> items;
    private static final List<Location> locations;
    private static final Location playerStartLocation;
    private static final Inventory playerStartInventory;

    // initialize game objects, game objects combination rules
    static {
        items = new ArrayList<>();
        Item well = new Item("колодец", "Глубокий и пахнет сыростью. Интересно, а старик воду фильтрует прежде чем пить?", false);
        items.add(well);
        Item mage = new Item("маг", "Храпит, старый хрен. Как бы у тебя кристалл вытащить?", false);
        items.add(mage);
        Item burner = new Item("горелка", "Большая, но еще теплая. Наверняка недавно работала. Интересно," +
                " ее можно использовать не по назначению?", false);
        items.add(burner);

        Item frog = new Item("лягушка", "Тыкаешь - квакает. Не трогаешь - сидит и внимательно на тебя смотрит.", true);
        items.add(frog);
        Item frogAndMarker = new Item("лягушка", "Перестала квакать и заметно удлиннилась. Наверное, маркер мешается.", true);
        items.add(frogAndMarker);
        Item crystal = new Item("кристалл", "Такой глубокий, синий цвет. Так и манит окунуться и забыться.", true);
        items.add(crystal);
        Item bucket = new Item("ведро", "Ржавое, но без дырок. Еще послужит, если использовать осторожно.", true);
        items.add(bucket);
        Item bucketAndChain = new Item("ведро", "С цепью кажется от него будет больше пользы чем без. Но толку от цепи, валяющейся на дне?", true);
        items.add(bucketAndChain);
        Item chainedBucket = new Item("ведро", "Цепь крепко припаяна. Но зачем?", true);
        items.add(chainedBucket);
        Item bucketAndWater = new Item("ведро", "Полное воды. Хоть цветы поливай, хоть себя, хоть лягушку топи.", true);
        items.add(bucketAndWater);
        Item chain = new Item("цепь", "Длинная и легкая. Вот бы такую куда-нибудь закинуть!", true);
        items.add(chain);
        Item marker = new Item("маркер", "Черный, затасканный. Наверное он несмываемый, потому что пахнет бодряще.", true);
        items.add(marker);

        combos = new ArrayList<>();
        combos.add(new Combo(frog, marker, frogAndMarker, "", true, true));
        combos.add(new Combo(frog, well, null, "'Что я там забыла? Сам туда лезь.', - внезапно говорит " +
                "лягушка человеческим голосом. Причем и не женским и не мужским.", false, false));
        combos.add(new Combo(frog, mage, null, "Лягушка шлепается на лицо старику и долго елозит по нему," +
                "но тот не просыпается. Лягушка грустнеет и возвращается к хозяину.", false, false));
        combos.add(new Combo(frog, bucket, null, "Лягушка смотрит на хозяина как на идиота и не понимает " +
                " зачем ей прыгать в пустое ведро.", false, false));
        combos.add(new Combo(frog, crystal, null, "Лягушка садится на кристалл и резко начинает синеть. " +
                "Потом крепко выругивается, кастует себе портал и исчезает в нем.", true, false));
        combos.add(new Combo(frog, bucketAndWater, null, "Лягцшка радостно плюхается в ведро и долго там " +
                "плещется. Потом довольная вылезает обратно и возвращается к хозяину.", false, false));

        combos.add(new Combo(frogAndMarker, bucketAndWater, null, "Лягушка плюхается в воду. " +
                "Пытается поплавать, но благодаря маркеру постоянно всплывает. Она просится обратно на руки " +
                "и персонаж забирает ее из ведра.", false, false));

        combos.add(new Combo(chain, bucket, bucketAndChain, "Цепь положена в ведро. " +
                "Но это выглядит как-то странно. Может лучше прикрепить ее понадежнее?", true, true));
        combos.add(new Combo(bucketAndChain, burner, chainedBucket, "Цепь приварена к ведру.", true, false));
        combos.add(new Combo(chainedBucket, well, bucketAndWater, "В ведро набрана до краев колодезная вода.", true, false));
        combos.add(new Combo(bucketAndWater, mage, crystal, "Маг щедро обливается студеной водой " +
                "из ржавого ведра. Он что-то сурово мычит и переворачивается на другой бок." +
                "откуда-то из-под его пазухи на пол выпадает что-то блеснувшшее синим цветом.", false, false));

        combos.add(new Combo(marker, frog, null, "Лягушке нарисовали контуры очков. Теперь она похожа " +
                "на разработчика квеста.", false, false));
        combos.add(new Combo(marker, bucket, null, "Ведро аккуратно помечается именем хозяина.  " +
                "Так оно выглядит гораздо солиднее.", false, false));
        combos.add(new Combo(marker, mage, null, "На проплешине старика компактно дублируется сегодняшний лог с дев сервера. " +
                "На у него теперь красуется: 'PASSED WITH MINOR DEFECTS'.", false, false));

        locations = new ArrayList<>();
        locations.add(new Location("", "", new Inventory(), new HashMap<Directions, Location>()));

        playerStartLocation = new Location("", "", new Inventory(), new HashMap<Directions, Location>());
        playerStartLocation = new Location("", "", new Inventory(), new HashMap<Directions, Location>());
        playerStartLocation = new Location("", "", new Inventory(), new HashMap<Directions, Location>());
        playerStartInventory = new Inventory();
        playerStartInventory.add(marker);
    }

    public static Combo findCOmbo(Item object, Item target) {
        for (Combo combo : combos) {
            if (combo.object == object && combo.target == target) {
                return combo;
            }
        }
        return null;
    }

    public static Player createPlayer(String name) {
        return new Player(name, playerStartInventory, playerStartLocation);
    }
}
