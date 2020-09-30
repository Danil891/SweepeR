package elementsForStream;

public class Flags {

    private Field flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Field((Box.CLOSED));
        countOfClosedBoxes = FieldsFiller.getSize().x * FieldsFiller.getSize().y;
    }

    Box get(Coordinates coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coordinates coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    void toggleFlaggedToBox(Coordinates coord) {
        switch (flagMap.get(coord)) {
            case FLAGGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlaggedToBox(coord);
                break;
        }
    }

    void setFlaggedToBox(Coordinates coord) {
        flagMap.set(coord, Box.FLAGGED);
    }

    void setClosedToBox(Coordinates coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coordinates coord) {
        flagMap.set(coord, Box.BOMBED);

    }

    void setOpenedToClosedBombBox(Coordinates coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNoBombToFlaggedSafeBox(Coordinates coord) {
        if (flagMap.get(coord) == Box.FLAGGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlaggedBoxesAround(Coordinates coord) {
        int count = 0;
        for (Coordinates around : FieldsFiller.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGGED)
                count++;
        return count;
    }
}
