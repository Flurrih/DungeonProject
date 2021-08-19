package data;

import java.util.Objects;

public class DirectionRoom {
    private Room room;
    private char direction;

    public DirectionRoom(char direction, Room room) {
        this.room = room;
        this.direction = direction;
    }

    public Room getRoom() {
        return room;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionRoom that = (DirectionRoom) o;
        return direction == that.direction && room.equals(that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, direction);
    }
}
