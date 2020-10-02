package elementsForStream;

public class Game {

    private static Bomb bomb;

    private Flags flag;
    private GameState state;
    public boolean isFirst = true;
    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {
        FieldsFiller.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flags();
    }

    public static int getBombs() {
        return bomb.getTotalBombs();
    }

    public void start() {

        flag.start();
        state = GameState.PLAYED;
        isFirst = true;
    }

    public Box getBox(Coordinates coord) {
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    private Box getFirstBox(Coordinates coord) {
        return bomb.get(coord);
    }

    public void pressLeftButton(Coordinates coord) {
        if (isFirst) {
            isFirst = false;
            bomb.restart(coord);
            openBox(coord);
        } else {
            if (gameOver()) return;
            openBox(coord);
            checkWinner();
        }
    }

    private void checkWinner() {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Coordinates coord) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coord);
                return;
            case FLAGGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber(Coordinates coord) {
        if (bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlaggedBoxesAround(coord) == bomb.get(coord).getNumber())
                for (Coordinates around : FieldsFiller.getCoordsAround(coord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coordinates bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coordinates coord : FieldsFiller.getAllCoords())
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBombBox(coord);
            else flag.setNoBombToFlaggedSafeBox(coord);
    }

    private void openBoxesAround(Coordinates coord) {
        flag.setOpenedToBox(coord);
        for (Coordinates around : FieldsFiller.getCoordsAround(coord))
            openBox(around);
    }

    public void pressRightButton(Coordinates coord) {
        if (gameOver()) return;
        flag.toggleFlaggedToBox(coord);
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
