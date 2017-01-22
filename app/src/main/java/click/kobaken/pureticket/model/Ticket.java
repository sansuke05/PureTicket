package click.kobaken.pureticket.model;

import java.io.Serializable;
import java.util.ArrayList;

import click.kobaken.pureticket.R;


public class Ticket implements Serializable {
    public String name;
    public String amount;
    public String price;
    public int imageId;

    public Ticket() {
    }

    public Ticket(String name, String amount, String price, int imageId) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.imageId = imageId;
    }

    public static ArrayList<Ticket> createMocks() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("Vodka", "1000", "3.0$", R.drawable.vodka_only));
        tickets.add(new Ticket("Bread", "2000", "2.0$", R.drawable.bread_only));
        return tickets;
    }
}
