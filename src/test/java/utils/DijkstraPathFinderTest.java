package utils;

import controllers.MapController;
import data.GameMap;
import data.Room;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraPathFinderTest {

    DijkstraPathFinder dijkstraPathFinder;

    @Before
    public void init() {
        dijkstraPathFinder = new DijkstraPathFinder();
    }

    @Test
    public void shouldProperlyFindshortestPath() {
        String mapPath = "src/test/java/maps/testMap2";

        GameMap gameMap = new GameMap(MapDataLoader.getMapData(mapPath));
        MapController mapController = new MapController(gameMap);
        mapController.createPlayer("", "a0");

        String shortestPath =
                dijkstraPathFinder
                        .findShortestPath(gameMap.getAdjList(),
                                new Room("a0"),
                                new Room("a9"));

        assertEquals("nnesees",shortestPath);
    }
}