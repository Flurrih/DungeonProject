package data;

import java.util.Objects;

public class Room {
    private String label;

    public Room(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return label.equals(room.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
