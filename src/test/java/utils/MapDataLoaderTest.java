package utils;

import data.MapData;
import exceptions.DataValidationException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDataLoaderTest {

    private final String EXPECTED_SOURCE = "x1";
    private final char EXPECTED_DIRECTION = 'c';
    private final String EXPECTED_DESTINATION = "y1";
    private final MapData expectedMapData =
            new MapData(EXPECTED_SOURCE, EXPECTED_DIRECTION, EXPECTED_DESTINATION);

    @Test
    public void shouldProperlyReadMapDataFromString() throws DataValidationException {
        final String MAP_DATA_LINE = EXPECTED_SOURCE + " " + EXPECTED_DIRECTION + ":" + EXPECTED_DESTINATION;
        List<MapData> mapDataList = MapDataLoader.readMapDataFromString(MAP_DATA_LINE);

        assertEquals(expectedMapData, mapDataList.get(0));
    }

    @Test(expected = DataValidationException.class)
    public void shouldThrowExceptionWhenInvalidDataInString() throws DataValidationException {
        MapDataLoader.readMapDataFromString("x1 test1");
    }

    @Test
    public void shouldProperlyReadMapFromPath() {
        final String testGameMapPath = "src/test/java/maps/testMap";
        List<MapData> expectedMapDataFromPath = new ArrayList<>();
        expectedMapDataFromPath.add(expectedMapData);

        List<MapData> receivedMapData = MapDataLoader.getMapData(testGameMapPath);

        assertEquals(expectedMapDataFromPath, receivedMapData);
    }

}