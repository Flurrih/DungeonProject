package data;

import java.util.*;

public class Graph {
    private final Map<Room, List<DirectionRoom>> adjVertices;

    public Graph(List<MapData> mapData) {
        adjVertices = new HashMap<>();
        mapData.forEach(data -> {
            addRoom(data.source);
            addEdge(data.source, data.direction, data.destination);
        });
    }

    private void addRoom(String label) {
        adjVertices.putIfAbsent(new Room(label), new ArrayList<>());
    }

    private void addEdge(String source, char direction, String destination) {
        Room sourceRoom = new Room(source);
        Room destinationRoom = new Room(destination);
        DirectionRoom dirRoom = new DirectionRoom(direction, destinationRoom);
        adjVertices.get(sourceRoom).add(dirRoom);
    }

    public List<DirectionRoom> getDirectionRooms(Room room) {
        return adjVertices.get(room);
    }

    public Map<Room, List<DirectionRoom>> getAdjList() {
        return adjVertices;
    }
}
