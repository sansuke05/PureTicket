package click.kobaken.pureticket.model;

import java.io.Serializable;
import java.util.ArrayList;

import click.kobaken.pureticket.R;


public class Badge implements Serializable {
    public String name;
    public String description;
    public int imageId;
    public boolean state;

    public Badge() {
    }

    private Badge(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    public Badge(String name, String description, int imageId, boolean state) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.state = state;
    }

    public static ArrayList<Badge> createMocks() {
        ArrayList<Badge> badges = new ArrayList<>();
        badges.add(new Badge("StackOverflow", "Собери 100.000 прав одного типа", R.drawable.plum));
        badges.add(new Badge("Numismatist", "Собери 100 разных прав", R.drawable.plum));
        badges.add(new Badge("Jedi Exchanger", "Конвертируй 100.000 прав из одного вида в другой", R.drawable.plum));
        return badges;
    }
}
