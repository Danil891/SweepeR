package elementsForStream;

public class Bomb {

    private Field bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start() {
        bombMap = new Field(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coordinates coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = FieldsFiller.getSize().x * FieldsFiller.getSize().y / 2;
        if (totalBombs > maxBombs) totalBombs = maxBombs;
    }

    private void placeBomb() {
        while (true) {
            Coordinates coord = FieldsFiller.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord)) continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coordinates coord) {
        for (Coordinates around : FieldsFiller.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBombs;
    }
    void restart(Coordinates coord) {
        bombMap = new Field(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) placeBomb(coord);
    }

    private void placeBomb(Coordinates coord) {
        while (true) {
            Coordinates randomCoord = FieldsFiller.getRandomCoord();
            if (!coord.equals(randomCoord)) {
                if (Box.BOMB == bombMap.get(randomCoord)) continue;
                bombMap.set(randomCoord, Box.BOMB);
                incNumbersAroundBomb(randomCoord);
            } else continue;
            break;
        }
    }

}
