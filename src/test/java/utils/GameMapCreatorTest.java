package utils;

import data.MapData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMapCreatorTest {

    @Test
    public void shouldProperlyCreateDisplayMap() {
        List<MapData> mapData = new ArrayList<>();
        mapData.add(new MapData("s1", 'n', "d1"));
        mapData.add(new MapData("d1", 's', "s1"));

        String[][] displayMap = new GameMapCreator().createDisplayMap(mapData);
        assertEquals(3 , displayMap.length);
    }
}