package data;

public class Player {

    private final String name;
    private Room position;

    public Player(String name, Room position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }


    public Room getPosition() {
        return position;
    }

    public void setPosition(Room position) {
        this.position = position;
    }
}
