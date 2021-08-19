package controllers;

import data.GameMap;
import data.Room;
import utils.MapDataLoader;

import java.util.List;

public class GameController {

    private final MapController mapController;
    private final InputController inputController;

    public GameController() {
        String PLAYER_NAME = "**";
        String PLAYER_INITIAL_POSITION;
        String mapPath = "src/main/java/maps/map1";

        GameMap gameMap = new GameMap(MapDataLoader.getMapData(mapPath));
        PLAYER_INITIAL_POSITION = gameMap.getAdjList().keySet().iterator().next().getLabel();
        mapController = new MapController(gameMap);
        mapController.createPlayer(PLAYER_NAME, PLAYER_INITIAL_POSITION);
        inputController = new InputController();
    }

    public void play() {
        boolean isGameActive = true;
        while (isGameActive) {

            String possibleDirections = mapController.getPossibleDirections();

            mapController.renderGame();
            System.out.println("you are in room " + mapController.getPlayerPosition());
            System.out.println("possible moves: " + possibleDirections);
            System.out.print("your choice:");
            List<String> playerCommands = inputController.getUserInput();

            if (playerCommands.contains("find")) {
                String shortPath = mapController.getShortestPathToVertex(new Room(playerCommands.get(1)));
                System.out.println("finding path from " + mapController.getPlayerPosition() + " to " + playerCommands.get(1));
                System.out.println("the path is: " + shortPath);
            } else {
                playerCommands.forEach(mapController::movePlayer);
            }
            System.out.println("----------------------");
        }
    }
}
