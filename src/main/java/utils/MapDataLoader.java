package utils;

import data.MapData;
import exceptions.DataValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapDataLoader {
    static final String NEIGHBOUR_DIRECTION_SEPARATOR = ":";

    public MapDataLoader() { }

    public static List<MapData> getMapData(String mapPath) {
        List<MapData> mapDataList = new ArrayList<>();
        File file = new File(mapPath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                mapDataList.addAll(readMapDataFromString(st));
            }
        } catch(IOException | DataValidationException e) {
            System.out.println("[ERROR] Could not read map from file. " + e);
        }

        return mapDataList;
    }

    public static List<MapData> readMapDataFromString(String data) throws DataValidationException {
        List<MapData> mapDataList = new ArrayList<>();
        final int PARENT_INDEX = 0;
        final String NEIGHBOUR_DIRECTION_SEPARATOR = ":";

        if(data != null && data.length() > 0) {
            List<String> splittedData = splitParentFromNeighbours(data);
            String parentRoomName = splittedData.get(PARENT_INDEX);
            validateRoomName(parentRoomName);
            validateNeighboursExists(splittedData);
            for(int i = 1; i < splittedData.size(); i++) {
                String neighbour = splittedData.get(i);
                validateDirectionNeighbour(neighbour);
                String[] directionNeighbour = neighbour
                        .split(NEIGHBOUR_DIRECTION_SEPARATOR);
                char direction = directionNeighbour[0].charAt(0);
                String destinationVertex = directionNeighbour[1];
                mapDataList.add(new MapData(parentRoomName, direction, destinationVertex));
            }
        }

        return mapDataList;
    }

    private static List<String> splitParentFromNeighbours(String data) {
        final String SEPARATOR = " ";
        return Arrays.stream(data.split(SEPARATOR)).toList();
    }

    private static void validateRoomName(String roomName) {
        int roomLength = roomName.length();
        if (roomLength > 2) {
            System.out.println("[WARN] Room name [" + roomName + "] is too long [" + roomLength + "]. Max length of room should be 2");
        }
    }

    private static void validateDirectionNeighbour(String string) throws DataValidationException {
        if (!string.contains(NEIGHBOUR_DIRECTION_SEPARATOR)) {
            System.out.println("[ERROR] Validation of map data ended with failure. Wrong neighbour syntax: " + string);
            throw new DataValidationException("Wrong neighbour syntax.");
        }
    }

    private static void validateNeighboursExists(List<String> data) throws DataValidationException {
        if (data.size() <= 1) {
            System.out.println("[ERROR] Invalid number of neighbours provided");
            throw new DataValidationException("Invalid number of neighbours provided");
        }
    }
}
