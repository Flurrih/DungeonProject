package utils;

import data.MapData;

import java.util.*;

public class GameMapCreator {

    private final int mapSize;
    private final int constructMapSize;
    private boolean wasMapInitialized;
    private String[][] displayMap;
    private final Map<String, int[]> roomPositions;
    private final Queue<MapData> mapDataQueue;

    public GameMapCreator() {
        this.mapSize = 2*20;
        constructMapSize = 4 * mapSize - 1;
        wasMapInitialized = false;
        displayMap = new String[constructMapSize][constructMapSize];
        roomPositions = new HashMap<>();
        mapDataQueue = new LinkedList<>();
    }

    public String[][] createDisplayMap(List<MapData> mapData) {
        mapDataQueue.addAll(mapData);
        while (!mapDataQueue.isEmpty()) {
            MapData data = mapDataQueue.poll();
            feedMapWithData(data);
        }
        deleteEmptyRowsAndColumns();
        removeNullValuesFromMap();
        return displayMap;
    }

    private void removeNullValuesFromMap() {
        for (int i=0; i<displayMap.length; i++)
        {
            for(int j=0; j<displayMap[i].length; j++) {
                if(displayMap[i][j] == null) {
                    displayMap[i][j] = "  ";
                }
            }
        }
    }

    private void feedMapWithData(MapData mapData) {
        if(roomPositions.containsKey(mapData.source) || !wasMapInitialized) {
            int xCursor = mapSize, yCursor = mapSize;
            xCursor = roomPositions.getOrDefault(mapData.source, new int[]{xCursor, yCursor})[0];
            yCursor = roomPositions.getOrDefault(mapData.source, new int[]{xCursor, yCursor})[1];

            int xPath = xCursor;
            int yPath = yCursor;
            displayMap[xCursor][yCursor] = mapData.source;
            roomPositions.put(mapData.source, new int[]{xCursor, yCursor});
            boolean isVertical = true;

            if (mapData.direction == 'n') {
                xCursor -= 2;
                xPath -= 1;
                isVertical = true;
            } else if (mapData.direction == 'w') {
                yCursor -= 2;
                yPath -= 1;
                isVertical = false;
            } else if (mapData.direction == 'e') {
                yCursor += 2;
                yPath += 1;
                isVertical = false;
            } else if (mapData.direction == 's') {
                xCursor += 2;
                xPath += 1;
                isVertical = true;
            }

            displayMap[xCursor][yCursor] = mapData.destination;
            displayMap[xPath][yPath] = isVertical ? "|" : "-";
            roomPositions.put(mapData.destination, new int[]{xCursor, yCursor});
            wasMapInitialized = true;
        } else {
            mapDataQueue.add(mapData);
        }
    }

    private void deleteEmptyRowsAndColumns() {
        List<Integer> emptyRows = new ArrayList<>();
        List<Integer> emptyColumns = new ArrayList<>();

        for(int row = 0; row < constructMapSize; row++) {
            boolean rowNotContainsCharacter = true;
            boolean colNotContainsCharacter = true;
            for(int column = 0; column < constructMapSize; column++) {
                if(displayMap[row][column] != null) {
                    rowNotContainsCharacter = false;
                }

                if(displayMap[column][row] != null) {
                    colNotContainsCharacter = false;
                }
            }
            if(rowNotContainsCharacter) {
                emptyRows.add(row);
            }
            if(colNotContainsCharacter) {
                emptyColumns.add(row);
            }
        }

        deleteEmptyColumnsFromMap(emptyColumns);
        deleteEmptyRowsFromMap(emptyRows);
    }

    private void deleteEmptyColumnsFromMap(List<Integer> colsToDelete)
    {
        String[][] newMap = displayMap;
        if(displayMap != null && displayMap.length > 0 && displayMap[0].length > colsToDelete.size())
        {
            newMap = new String[displayMap.length][displayMap[0].length-colsToDelete.size()];
            for(int i=0; i<displayMap.length; i++)
            {
                int newColIdx = 0;
                for(int j=0; j<displayMap[i].length; j++)
                {
                    if(!colsToDelete.contains(j))
                    {
                        newMap[i][newColIdx] = displayMap[i][j];
                        newColIdx++;
                    }
                }
            }
        }
        displayMap = newMap;
    }

    private void deleteEmptyRowsFromMap(List<Integer> rowsToDelete)
    {
        String[][] newMap = displayMap;
        if(displayMap != null && displayMap.length > rowsToDelete.size() && displayMap[0].length > 0)
        {
            newMap = new String[displayMap.length - rowsToDelete.size()][displayMap[0].length];
            int newColIdx = 0;
            for (int i=0; i<displayMap.length; i++)
            {
                if (rowsToDelete.contains(i)) {
                    continue;
                }
                for (int j=0; j<displayMap[i].length; j++)
                {
                    newMap[newColIdx][j] = displayMap[i][j];
                }
                newColIdx ++;
            }
        }
        displayMap = newMap;
    }
}
