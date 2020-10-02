import org.junit.Test;
import elementsForStream.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class test {

@Test
public void getCoordsAround() {
    new Game(7, 7, 85);
    assertEquals(new ArrayList<>(Arrays.asList(new Coordinates(4, 5), new Coordinates(5, 4), new Coordinates(5, 6),
            new Coordinates(6, 4), new Coordinates(6, 5), new Coordinates(6, 6))), FieldsFiller.getCoordsAround(new Coordinates(5, 5)));
    assertEquals(new ArrayList<>(Arrays.asList(new Coordinates(0, 1), new Coordinates(0, 2), new Coordinates(0, 3),
            new Coordinates(1, 1), new Coordinates(1, 3), new Coordinates(2, 2))), FieldsFiller.getCoordsAround(new Coordinates(1, 2)));
    assertEquals(new ArrayList<>(Arrays.asList(new Coordinates(0, 1), new Coordinates(1, 0))),
            FieldsFiller.getCoordsAround(new Coordinates(0, 0)));
}

    @Test
    public void fixBombsCount() {
        new Game(4, 4, 3);
        assertEquals(3, Game.getBombs());
        new Game(7, 7, 85);
        assertEquals(24, Game.getBombs());
        new Game(6, 6, 24);
        assertEquals(18, Game.getBombs());
    }

}
