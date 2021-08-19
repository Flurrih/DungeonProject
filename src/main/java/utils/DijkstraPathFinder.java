package utils;

import data.DirectionRoom;
import data.Room;

import java.util.*;

public class DijkstraPathFinder {

    private Map<Room, List<DirectionRoom>> adj_list;
    private Set<Room> visited;
    private Map<Room, Integer> dist;
    private Map<Room, String> shortestPath;
    private Queue<Room> pqueue;

    public String findShortestPath(Map<Room, List<DirectionRoom>> adj_list, Room src_room, Room dest) {
        this.adj_list = adj_list;
        int V = adj_list.size();
        dist = new HashMap<>();
        shortestPath= new HashMap<>();
        pqueue = new LinkedList<>();
        this.visited = new HashSet<>();

        for (Room vert : adj_list.keySet())
            dist.put(vert, Integer.MAX_VALUE);

        pqueue.add(src_room);

        dist.put(src_room, 0);
        shortestPath.put(src_room, "");
        while (visited.size() != V) {
            Room u = pqueue.remove();
            visited.add(u);
            graphAdjacentVertexes(u);
        }
        return shortestPath.get(dest);
    }

    private void graphAdjacentVertexes(Room u) {
        int edgeDistance;
        int newDistance;
        String newPath;

        for (int i = 0; i < adj_list.get(u).size(); i++) {
            DirectionRoom v = adj_list.get(u).get(i);
            if (!visited.contains(v.getRoom())) {
                edgeDistance = 1;
                newDistance = dist.get(u) + edgeDistance;
                newPath = shortestPath.get(u).concat(String.valueOf(v.getDirection()));

                if (newDistance < dist.get(v.getRoom())) {
                    dist.put(v.getRoom(), newDistance);
                    shortestPath.put(v.getRoom(), newPath);
                }
                pqueue.add(v.getRoom());
            }
        }
    }
}
