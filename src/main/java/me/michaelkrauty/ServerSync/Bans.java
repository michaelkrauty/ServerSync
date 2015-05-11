package me.michaelkrauty.ServerSync;

import java.util.ArrayList;

/**
 * Created by micha_000 on 4/21/2015.
 */
public class Bans {

    private final Main main;
    public ArrayList<String> bans = new ArrayList<String>();

    public Bans(Main main) {
        this.main = main;
    }

    public ArrayList<String> getBans() {
        return bans;
    }
}
