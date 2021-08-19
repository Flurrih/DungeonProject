package data;

import utils.GameMapCreator;

import java.util.List;

public class GameMap extends Graph {

    private final String[][] displayMap;
    private final GameMapCreator gameMapCreator;

    public GameMap(List<MapData> mapDataList) {
        super(mapDataList);
        this.gameMapCreator = new GameMapCreator();
        this.displayMap = gameMapCreator.createDisplayMap(mapDataList);
    }

    public String[][] getMap() {
        return displayMap;
    }
}
