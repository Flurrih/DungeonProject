package controllers;

import data.DirectionRoom;
import data.GameMap;
import data.Player;
import data.Room;
import utils.DijkstraPathFinder;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataProcessingException;

import java.util.*;

public class MapController {

    private final GameMap gameMap;
    private Player player;
    private final DijkstraPathFinder dijkstraPathFinder;

    public MapController(GameMap gameMap) {
        this.gameMap = gameMap;
        dijkstraPathFinder = new DijkstraPathFinder();
    }

    public void createPlayer(String name, String initialPosition) {
        this.player = new Player(name, new Room(initialPosition));
    }

    public void renderGame() {
        MapRenderer.render(player, gameMap);
    }

    public String getPossibleDirections() throws DataProcessingException {
        List<DirectionRoom> possibleMoves = getPossibleMoves(player.getPosition());
        StringBuilder directions = new StringBuilder();
        if (possibleMoves == null || possibleMoves.isEmpty()) {
            System.out.println("[ERROR] Could not find any possible moves for player position:" + player.getPosition().getLabel());
        } else {
            possibleMoves.forEach(dirs -> directions.append(dirs.getDirection()));
        }
        return directions.toString();
    }

    public String getPlayerPosition() {
        return player.getPosition().getLabel();
    }

    public void movePlayer(String direction) {
        Optional<DirectionRoom> optDV = getPossibleMoves(player.getPosition()).stream()
                .filter(e -> e.getDirection() == direction.charAt(0))
                .findFirst();
        optDV.ifPresent(directionRoom -> player.setPosition(directionRoom.getRoom()));
    }

    private List<DirectionRoom> getPossibleMoves(Room room) {
        return gameMap.getDirectionRooms(room);
    }


    public String getShortestPathToVertex(Room destination) {
        return dijkstraPathFinder.findShortestPath(gameMap.getAdjList(), player.getPosition(), destination);
    }
}
