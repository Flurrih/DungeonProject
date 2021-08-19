package controllers;

import data.GameMap;
import utils.MapDataLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapControllerTest {

    final String mapPath = "src/test/java/maps/testMap2";
    final String PLAYER_NAME = "**";
    final String PLAYER_POSITION = "a0";

    MapController mapController;

    @Test
    public void shouldCreatePlayer() {
        GameMap gameMap = new GameMap(MapDataLoader.getMapData(mapPath));
        MapController mapController = new MapController(gameMap);
        mapController.createPlayer(PLAYER_NAME, PLAYER_POSITION);

        assertEquals(PLAYER_POSITION, mapController.getPlayerPosition());
    }

    @Test
    public void shouldGetPossibleDirections() {
        resetMap();
        final String possibleDirections = "n";

        final String result = mapController.getPossibleDirections();

        assertEquals(possibleDirections, result);
    }

    @Test
    public void shouldMovePlayerByOneRoom() {
        resetMap();
        final String direction = "n";

        mapController.movePlayer(direction);

        assertEquals("a3", mapController.getPlayerPosition());
    }

    private void resetMap() {
        GameMap gameMap = new GameMap(MapDataLoader.getMapData(mapPath));
        mapController = new MapController(gameMap);
        mapController.createPlayer(PLAYER_NAME, PLAYER_POSITION);
    }

}