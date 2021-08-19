package data;

import java.util.Objects;

public class MapData {
    public String source;
    public char direction;
    public String destination;

    public MapData(String source, char direction, String destination) {
        this.source = source;
        this.direction = direction;
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapData mapData = (MapData) o;
        return direction == mapData.direction && source.equals(mapData.source) && destination.equals(mapData.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, direction, destination);
    }
}
