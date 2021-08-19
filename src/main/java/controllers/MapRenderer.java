package controllers;

import data.GameMap;
import data.Player;

public class MapRenderer {

    public static void render(Player player, GameMap gameMap) {
        for(String[] row : gameMap.getMap()) {
            renderRow(player, row);
        }
    }

    private static void renderRow(Player player, String[] row) {
        for (String i : row) {
            if (i.equals(player.getPosition().getLabel())) {
                i = player.getName();
            }
            System.out.printf("%3s", i);
        }
        System.out.println();
    }
}
